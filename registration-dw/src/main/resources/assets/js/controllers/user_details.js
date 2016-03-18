define(function(require) {
	var ko = require('knockout'),
		hasher = require('hasher'),
		crossroads = require('crossroads'),
		UserDetailsComponent = require('component/user_details_component'),
		LoadingComponent = require('common/loading_component'),
		ErrorComponent = require('common/error_component'),
		InfoComponent = require('common/info_component'),
		loginServiceClient = require('serviceClient/login_service_client')
		;

	require('common/formatMessage');


	function UserDetails() {
		'use strict';

		var self = this;

		self.loadingComponent = new LoadingComponent();
		self.errorComponent = new ErrorComponent();
		self.infoComponent = new InfoComponent();
		
		self.userDetailsComponent = new UserDetailsComponent(this);
		
		self.targetUsername = ko.observable();
		
		self.init = function() {
			console.log("init");
			self.errorComponent.hide();
			self.infoComponent.hide();
			self.loadingComponent.start(300);
			
			loginServiceClient.getConsumerDetails().done(function(response) {
				console.log("get data success");
				self.userDetailsComponent.bindData(response);
				self.loadingComponent.stop();
			}).fail(function(jqXHR, textStatus) {

				self.errorComponent.setErrorMessages(null);
				self.errorComponent.show();

				console.log("Request failed: " + textStatus);

				self.loadingComponent.stop();
			});
		};
		
		self.update = function(request) {
			self.errorComponent.hide();
			self.infoComponent.hide();
			self.loadingComponent.start(300);

			loginServiceClient.update(request).done(function(response) {
				self.infoComponent.setInfoMessages(["User updated successfully"]);
				self.infoComponent.show();
				self.loadingComponent.stop();
			}).fail(function(jqXHR, textStatus) {

				self.errorComponent.setErrorMessages(null);
				self.errorComponent.show();

				console.log("Request failed: " + textStatus);

				self.loadingComponent.stop();
			});
		};
		
		self.checkPasswordLevel = function(request) {
			loginServiceClient.checkPasswordLevel(request).done(function(response) {
				console.log('check password level done');
				self.registrationComponent.displayPasswordLevel(response.level);			
			}).fail(function(jqXHR, textStatus) {
				self.errorComponent.setErrorMessages(null);
				self.errorComponent.show();

				console.log("Request failed: " + textStatus);
			});
		};
		
		self.backToLogin = function() {
			window.location.href ="/kyip/v1/landing";
		};
	};

	$(function() {
		var viewModel = new UserDetails();
		ko.applyBindings(viewModel);
		viewModel.init();
	});
});