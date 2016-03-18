define(function(require) {
	var ko = require('knockout');
	
	function InfoComponent() {
		'use strict';
		
		var self = this;
		self.displayInfo = ko.observable(false);
		self.infoMessages = ko.observableArray([]);

		self.setInfoMessages = function(infoMessages) {
			self.infoMessages(infoMessages);
		}
		
		self.show = function() {
			self.displayInfo(true);
		}
		
		self.hide = function() {
			self.displayInfo(false);
		}
	}
	
	return InfoComponent;
});