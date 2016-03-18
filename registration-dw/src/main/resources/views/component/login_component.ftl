<form class="login" data-bind="submit: onLoginButtonClicked">
	<h4>Login</h4>
	<div class="row">
		<input class="form-control" name="email" id="email" placeholder="Email" data-bind="value: email"/>
	</div>
	<div class="row">
		<input class="form-control" name="password" id="password" placeholder="password" data-bind="value: password"/>
	</div>
	<div class="row">
		<button class="btn btn-primary search-button text-uppercase" type="submit">Login</button>
	</div>
	<div class="row">
		<button class="btn btn-primary" data-bind="click: register">Register</button>
	</div>
</form>