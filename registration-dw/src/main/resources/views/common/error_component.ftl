<div class="alert alert-danger" data-bind="visible: displayError" style="display: none;">
	<span class="glyphicon glyphicon-exclamation-sign"></span> 
	<span data-bind="if: errorMessages">
		<span data-bind="text: errorMessages()[0]"></span>
	</span>
	<span data-bind="ifnot: errorMessages">
		error.technical.difficulties
	</span>
</div>