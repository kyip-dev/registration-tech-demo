define(["moment"], function(moment) {

	function Utils() {
	}

	Utils.prototype = {
		formatDate : function(time) {
			var date = moment.utc(time);
			return date.format('YYYY-MM-DD');
		},
		formatDateTime : function(time) {
			var date = moment.utc(time);
			return date.format('YYYY-MM-DD HH:mm:ss');
		},
		formatDateTimeWithoutSeconds : function(time) {
			var date = moment.utc(time);
			return date.format('YYYY-MM-DD HH:mm');
		},
		getCurrentTime : function() {
			return moment().utc().format('YYYY-MM-DD HH:mm:ss');
		},
		getCurrentISODateTime : function() {
			return moment.utc().toISOString();
		},
		getOffsetISODateTime : function(offset, unit) {
			return moment.utc().add(offset, unit).toISOString();
		}
	};

	return new Utils();
});
