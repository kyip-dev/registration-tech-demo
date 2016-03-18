define(function(require) {
	var ko = require('knockout'),
		utils = require('common/utils');

	function LoginComponent(parentComponent) {
		'use strict';

		var self = this;
		self.parentComponent = parentComponent;
		self.email = ko.observable();
		self.password = ko.observable();

		self.onLoginButtonClicked = function() {
			var request = self.createRequest();
			self.parentComponent.login(request);
		};
		
		self.register = function() {
			self.parentComponent.register();
		}
		
		self.createRequest = function() {
			var request = {
				username: self.email(),
				password: self.password()
			};
			return request;
		}

	}

	return LoginComponent;
});