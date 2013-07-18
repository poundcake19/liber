<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Liber - ${title}</title>
	<link href="<@spring.url "/bootstrap/bootstrap.min.css"/>" rel="stylesheet"/>
	<script src="<@spring.url "/bootstrap/bootstrap.min.js"/>"></script>
	<#if scripts?? >
		<#list scripts as script>
			<script src="${script}"></script>
		</#list>
	</#if>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<h1>${title}</h1>
		</div>
		<div class="row-fluid">
			<div class="navbar">
				<div class="navbar-inner">
					<ul class="nav">
						<li><a href="<@spring.url "/"/>">Home</a></li>
						<li><a href="<@spring.url "/createArticle"/>">Create Article</a></li>
						<li><a href="<@spring.url "/createTag"/>">Create Tag</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="row-fluid">