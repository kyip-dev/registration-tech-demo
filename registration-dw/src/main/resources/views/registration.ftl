<script data-main="${scriptPath}" src="/assets/js/lib/require.js"></script>

<div data-bind="with: loadingComponent">
	<#include "/views/common/loading_component.ftl"/>
</div>

<div data-bind="with: errorComponent">
	<#include "/views/common/error_component.ftl"/>
</div>

<div data-bind="with: infoComponent">
	<#include "/views/common/info_component.ftl"/>
</div>

<div class="row">
	<div class="col-md-12" data-bind="with: registrationComponent">
		<#include "/views/component/registration_component.ftl"/>
	</div>
</div>