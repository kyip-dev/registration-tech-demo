define(function(require) {
	var ko = require('knockout'),
		hasher = require('hasher'),
		crossroads = require('crossroads'),
		RegistrationComponent = require('component/registration_component'),
		LoadingComponent = require('common/loading_component'),
		ErrorComponent = require('common/error_component'),
		InfoComponent = require('common/info_component'),
		loginServiceClient = require('serviceClient/login_service_client')
		;

	require('common/formatMessage');


	function Registration() {
		'use strict';

		var self = this;

		self.loadingComponent = new LoadingComponent();
		self.errorComponent = new ErrorComponent();
		self.infoComponent = new InfoComponent();
		
		self.registrationComponent = new RegistrationComponent(this);
		
		self.register = function(request) {
			self.errorComponent.hide();
			self.infoComponent.hide();
			self.loadingComponent.start(300);

			loginServiceClient.register(request).done(function(response) {
				self.infoComponent.setInfoMessages(["User created successfully"]);
				self.infoComponent.show();
				self.loadingComponent.stop();
			}).fail(function(jqXHR, textStatus) {
				//self._unpairDeviceFailure();

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
		var viewModel = new Registration();
		ko.applyBindings(viewModel);
		//viewModel.init();
	});
});