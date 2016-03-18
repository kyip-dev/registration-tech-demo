define(function(require) {
	var ko = require('knockout');
	
	function LoadingComponent() {
		'use strict';
		
		var self = this;
		self.displayLoading = ko.observable(false);
		self.intervalId = null;
		
		self.start = function(delay) {
			if (self.intervalId) {
				console.log('Interval is set already');
				return false;
			}
			
			if (0 === delay) {
				self.displayLoading(true);
			} else {
				self.intervalId = window.setInterval(function() {
					self.displayLoading(true);
				}, delay);
			}
			
			return true;
		}
		
		self.stop = function() {
			if (self.intervalId) {
				window.clearInterval(self.intervalId);
				self.intervalId = null;
			}
			self.displayLoading(false);
		}
	}
	
	return LoadingComponent;
});