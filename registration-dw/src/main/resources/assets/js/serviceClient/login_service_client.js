define(["jquery"], function($) {

	function CopytableServiceClient() {
	}

	CopytableServiceClient.prototype = {
		getLists : function(emailAddr, phoneNumber, name) {
			return $.ajax({
				headers : {
					'Accept' : 'application/json',
					'Content-Type' : 'application/json',
					'Cache-Control' : 'no-cache'
				},
				url : 'consumers',
				dataType : "json",
				method : "GET",
				data : {
					emailAddr : emailAddr,
					phoneNumber : phoneNumber,
					name : name
				}
			});
		},
		
		login : function(request) {
			return $.ajax({
				headers : {
					'X-Requested-By' : 'aqUi9aQZUUZ0gEVL',
					'Accept' : 'application/json',
					'Content-Type' : 'application/json',
					'Cache-Control' : 'no-cache'
				},
				url : "/kyip/v1/landing/login",
				dataType : "json",
				type : "POST",
				data : JSON.stringify(request)
			});
		},
		
		register : function(request) {
			return $.ajax({
				headers : {
					'X-Requested-By' : 'aqUi9aQZUUZ0gEVL',
					'Accept' : 'application/json',
					'Content-Type' : 'application/json',
					'Cache-Control' : 'no-cache'
				},
				url : "/kyip/v1/registration/register",
				dataType : "json",
				type : "POST",
				data : JSON.stringify(request)
			});
		},
		
		checkPasswordLevel : function(request) {
			return $.ajax({
				headers : {
					'X-Requested-By' : 'aqUi9aQZUUZ0gEVL',
					'Accept' : 'application/json',
					'Content-Type' : 'application/json',
					'Cache-Control' : 'no-cache'
				},
				url : "/kyip/v1/registration/password/strength",
				dataType : "json",
				type : "POST",
				data : JSON.stringify(request)
			});
		},
		
		getConsumerDetails : function() {
			return $.ajax({
				headers : {
					'Accept' : 'application/json',
					'Content-Type' : 'application/json',
					'Cache-Control' : 'no-cache'
				},
				method : "GET"
			});
		},


		registerGetProjectEvent : function() {
			return $.ajax({
				headers : {
					'Accept' : 'application/json',
					'Content-Type' : 'application/json',
					'Cache-Control' : 'no-cache'
				},
				url : "/kyip/v1/copytable/registration/events/projects",
				dataType : "json",
				method : "GET"
			});
		},

		getData : function(path) {
			return $.ajax({
				headers : {
					'Accept' : 'application/json',
					'Content-Type' : 'application/json',
					'Cache-Control' : 'no-cache'
				},
				url : "/kyip/v1/copytable" + path,
				dataType : "json",
				method : "GET"
			});
		},

		saveCurrentProject : function(request) {
			return $.ajax({
				headers : {
					'X-Requested-By' : 'aqUi9aQZUUZ0gEVL',
					'Accept' : 'application/json',
					'Content-Type' : 'application/json',
					'Cache-Control' : 'no-cache'
				},
				url : "/kyip/v1/copytable/currentProject",
				dataType : "json",
				data: JSON.stringify(request),
				method : "POST",
			});
		},


		deleteConsumer : function() {
			return $.ajax({
				headers : {
					'X-Requested-By' : 'aqUi9aQZUUZ0gEVL',
					'Accept' : 'application/json',
					'Content-Type' : 'application/json',
					'Cache-Control' : 'no-cache'
				},
				url : window.location.pathname,
				dataType : "json",
				method : "DELETE",
			});
		},

		unpairDevice : function(deviceId) {
			var encodedDeviceId = encodeURIComponent(deviceId);

			return $.ajax({
				headers : {
					'X-Requested-By' : 'aqUi9aQZUUZ0gEVL',
					'Accept' : 'application/json',
					'Content-Type' : 'application/json',
					'Cache-Control' : 'no-cache'
				},
				url : window.location.pathname + "/devices/" + encodedDeviceId + "/unpair",
				dataType : "json",
				method : "DELETE",
			});
		},

		getTransactionDetails : function(transactionReference) {
			return $.ajax({
				headers : {
					'Accept' : 'application/json',
					'Content-Type' : 'application/json',
					'Cache-Control' : 'no-cache'
				},
				url : window.location.pathname + "/transactions" + "/" + transactionReference,
				dataType : "json",
				method : "GET",
			});
		},

	};

	return new CopytableServiceClient();
});
