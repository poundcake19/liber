<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Liber - ${title}</title>
	<link href="<@spring.url "/bootstrap/bootstrap.min.css"/>" rel="stylesheet"/>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
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
						<li><a href="<@spring.url "/"/>"><i class="icon-home"></i>Home</a></li>
						<li>
							<a href="<@spring.url "/createArticle"/>">
								<i class="icon-file"></i>Create Article
							</a>
						</li>
						<li>
							<a href="<@spring.url "/createTag"/>">
								<i class="icon-tag"></i>Create Tag
							</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="row-fluid">