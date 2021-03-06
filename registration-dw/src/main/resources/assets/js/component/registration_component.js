define(function(require) {
	var ko = require('knockout'),
		utils = require('common/utils');

	function RegistrationComponent(parentComponent) {
		'use strict';

		var self = this;
		self.parentComponent = parentComponent;
		self.email = ko.observable();
		self.password = ko.observable();
		self.cpassword = ko.observable();
		self.nickname = ko.observable();
		self.passwordLevel = ko.observable();

		self.onRegisterButtonClicked = function() {
			var request = self.createRequest();
			self.parentComponent.register(request);
		};
		
		self.backToLogin = function() {
			self.parentComponent.backToLogin();
		};
		
		self.checkPasswordLevel = function() {
			var request = {
				password: self.password()
			};
			self.parentComponent.checkPasswordLevel(request);
		};
		
		self.displayPasswordLevel = function(value) {
			var level = "Weak";
			if (value == 0) {
				level = "Strong";
			} else if (value == 1) {
				level = "Normal";
			}
			self.passwordLevel(level);
		};
		
		self.createRequest = function() {
			var request = {
				username: self.email(),
				password: self.password(),
				passwordConfirm: self.cpassword(),
				nickname: self.nickname()
			};
			return request;
		}

	}

	return RegistrationComponent;
});