<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>reg</title>
    <base href="/">

    <!-- Stylesheets -->
	<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css">
	<link rel="stylesheet" href="/assets/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/assets/css/sticky-footer-navbar.css"/>
    <link rel="stylesheet" href="/assets/css/main.css"/>
</head>
	<body>
		<div class="main-content">
			<div class="container-fluid">
				<#if sectionTemplatePath??>
					<#include sectionTemplatePath>
				</#if>
			</div>
		</div>
	</body>
</html>
