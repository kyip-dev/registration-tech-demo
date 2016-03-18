define(function(require) {
	var ko = require('knockout');
	
	function ErrorComponent() {
		'use strict';
		
		var self = this;
		self.displayError = ko.observable(false);
		self.errorMessages = ko.observableArray([]);

		// TODO: Add error level
		
		self.setErrorMessages = function(errorMessages) {
			self.errorMessages(errorMessages);
		}
		
		self.show = function() {
			self.displayError(true);
		}
		
		self.hide = function() {
			self.displayError(false);
		}
	}
	
	return ErrorComponent;
});