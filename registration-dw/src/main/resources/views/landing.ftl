<script data-main="${scriptPath}" src="/assets/js/lib/require.js"></script>

<div data-bind="with: loadingComponent">
	<#include "/views/common/loading_component.ftl"/>
</div>

<div data-bind="with: errorComponent">
	<#include "/views/common/error_component.ftl"/>
</div>

<div class="row">
	<div class="col-md-12" data-bind="with: loginComponent">
		<#include "/views/component/login_component.ftl"/>
	</div>
</div>