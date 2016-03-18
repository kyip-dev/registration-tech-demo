package com.kyip.api.registration.external.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.inject.Inject;
import com.kyip.api.registration.external.model.User;
import com.kyip.api.registration.external.model.session.UserSession;
import com.kyip.api.registration.external.websocket.MessageSender;

public class LandingService {
	private static final Logger logger = LoggerFactory.getLogger(LandingService.class);

	// TODO refactor to get from config
	private final String dbUrl = "https://amber-torch-8801.firebaseio.com/";
	private final String dbRefName = "users";

	@Inject
	private MessageSender messageSender;


	/**
	 * Get the copytable projects catalog
	 * Store the catalog to session
	 * Push websocket to trigger frontend to get catalog from session
	 *
	 * @param session to store the projects catalog
	 */
	public void getUserEvent(final HttpSession session) {
		Firebase myFirebaseRef = new Firebase(dbUrl);

		myFirebaseRef.child(dbRefName).addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot snapshot) {
				List<User> users= new ArrayList<>();

				for (DataSnapshot userSnapshot: snapshot.getChildren()) {
					User u = userSnapshot.getValue(User.class);
					users.add(u);
				}
				// put session
				UserSession cs = (UserSession)session.getAttribute(UserSession.SESSION_KEY);
				if (cs== null) {
					cs = new UserSession();
				}
				cs.setUsers(users);
				session.setAttribute(UserSession.SESSION_KEY, cs);
				System.out.println("session get: " + session.getAttribute(UserSession.SESSION_KEY));

				// web socket call frontend to get again
				messageSender.sendMessage("/projects");
				// get session object
			}

			@Override
			public void onCancelled(FirebaseError error) {
			}
		});
	}

	public void saveUser(User user, String entryName) {
		Firebase myFirebaseRef = new Firebase(dbUrl);
		Firebase userRef = myFirebaseRef.child("users").child(entryName);
		userRef.setValue(user);
	}
}
