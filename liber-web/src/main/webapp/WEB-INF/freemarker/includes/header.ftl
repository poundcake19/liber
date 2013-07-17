<#import "/spring.ftl" as spring/>
<html>
<head>
	<title>Liber - ${title}</title>
	<#if scripts?? >
		<#list scripts as script>
			<script src="${script}"></script>
		</#list>
	</#if>
</head>
<body>
	<h1>${title}</h1>
	<div>
		<ul>
			<li><a href="<@spring.url "/"/>">Home</a></li>
			<li><a href="<@spring.url "/createArticle"/>">Create Article</a></li>
			<li><a href="<@spring.url "/createTag"/>">Create Tag</a></li>
		</ul>
	</div>
	<div>