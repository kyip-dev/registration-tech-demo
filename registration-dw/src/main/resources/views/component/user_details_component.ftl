<form class="login" data-bind="submit: onUpdateButtonClicked">
	
	<h4>User Details</h4>
	<div class="row">
		<div class="col-md-4">
			<label class="">Email</label>
		</div>
		<div class="col-md-6">
			<input class="form-control" name="email" id="email" placeholder="Email" data-bind="value: email"/>
		</div>
		<div class="col-md-2"></div>
	</div>
	<div class="row">
		<div class="col-md-4">
			<label class="">Password</label>
		</div>
		<div class="col-md-6">
			<input class="form-control" name="password" id="password" placeholder="password" type="password "data-bind="value: password,  valueUpdate: 'afterkeydown', event: { keyup: checkPasswordLevel}"/>
		</div>
		<div class="col-md-2">
			<span data-bind="text: passwordLevel"></span>
		</div>
	</div>
	<div class="row">
		<div class="col-md-4">
			<label class="">Confirm Password</label>
		</div>
		<div class="col-md-6">
			<input class="form-control" name="cpassword" id="cpassword" placeholder="confirm password" type="password" data-bind="value: cpassword"/>
		</div>
		<div class="col-md-2"></div>
	</div>
	<div class="row">
		<div class="col-md-4">
			<label class="">Nickname</label>
		</div>
		<div class="col-md-6">
			<input class="form-control" name="nickname" id="nickname" placeholder="nickname" data-bind="value: nickname"/>
		</div>
		<div class="col-md-2"></div>
	</div>
	<div class="row">
		<button class="btn btn-primary text-uppercase" type="submit">Update</button>
	</div>
</form>