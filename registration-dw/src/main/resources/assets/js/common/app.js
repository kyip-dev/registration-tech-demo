var development = false,
	version = '__MAVEN_PROJECT_VERSION' + '-' + '__MAVEN_BUILD_TIMESTAMP';

requirejs.config({
	"baseUrl" : "/assets/js",
	"paths" : {
		"jquery" : "lib/jquery-2.1.4.min",
		"knockout" : "lib/knockout-3.3.0.min",
		'knockout-switch-case' : 'lib/knockout-switch-case.min',
		"bootstrap" : "lib/bootstrap-3.3.5.min",
		"moment" : "lib/moment.min",
		"signals" : "lib/js-signals.min",
		"hasher" : "lib/hasher.min",
		"crossroads" : "lib/crossroads.min",
		"firebase" : "lib/firebase"
	},
	"shim" : {
		"bootstrap" : ["jquery"],
		"crossroads" : ["signals"]
	},
	"urlArgs" : "v=" + (development ? (new Date()).getTime() : version)
});
