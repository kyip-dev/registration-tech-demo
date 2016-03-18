define(function(require) {
	var utils = require('./utils');
	var ko = require('knockout');

	function _formatText(pattern, values) {
		if (!Array.isArray(values)) {
			values = [values];
		}
		var r = [];
		for (var i = 0; i < values.length; ++i) {
			var value = values[i];
			if (ko.isObservable(value)) {
				r.push(value());
			} else {
				r.push(value);
			}
		}
		return pattern.replace(/{(\d+)}/g, function(match, number) {
			return typeof r[number] != 'undefined' ? r[number] : match;
		});
	}

	ko.formatMessage = function(pattern, values) {
		return _formatText(pattern, values);
	};
});