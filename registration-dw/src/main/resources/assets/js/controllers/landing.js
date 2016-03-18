define(function(require) {
	var ko = require('knockout'),
		hasher = require('hasher'),
		crossroads = require('crossroads'),
		LoginComponent = require('component/login_component'),
		LoadingComponent = require('common/loading_component'),
		ErrorComponent = require('common/error_component'),
		loginServiceClient = require('serviceClient/login_service_client')
		;

	require('common/formatMessage');


	function Landing() {
		'use strict';

		var self = this;

		self.loadingComponent = new LoadingComponent();
		self.errorComponent = new ErrorComponent();
		
		self.loginComponent = new LoginComponent(this);
		
		self.login = function(request) {
			self.errorComponent.hide();
			self.loadingComponent.start(300);

			loginServiceClient.login(request).done(function(response) {
				window.location.href ="/kyip/v1/landing/details/" + response.username;
				
			}).fail(function(jqXHR, textStatus) {
				self.errorComponent.setErrorMessages(["Invalid Login"]);
				self.errorComponent.show();

				console.log("Request failed: " + textStatus);
				self.loadingComponent.stop();
			});
		};
		
		self.register = function() {
			window.location.href ="/kyip/v1/registration";
		}
	};

	$(function() {
		var viewModel = new Landing();
		ko.applyBindings(viewModel);
		//viewModel.init();
	});
});