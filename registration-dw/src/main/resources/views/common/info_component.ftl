<div class="alert alert-info" data-bind="visible: displayInfo" style="display: none;">
	<span class="glyphicon glyphicon-ok"></span> 
	<span data-bind="if: infoMessages">
		<span data-bind="text: infoMessages()[0]"></span>
	</span>
	<span data-bind="ifnot: infoMessages">
		no successful info
	</span>
</div>