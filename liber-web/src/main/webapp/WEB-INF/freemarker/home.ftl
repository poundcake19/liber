<html>
<head>
	<title>Liber - Home</title>
</head>
<body>
	<h1>Liber</h1>
	<div>
		<ul>
			<li><a href="createArticle"/>Create Article</a></li>
			<li><a href="createTag"/>Create Tag</a></li>
		</ul>
	</div>
	<p>Liber is an awesome web content management system!</p>
	<h2>Tags</h2>
	<#if (tags?size > 0) >
		<ul>
			<#list tags as tag>
				<li>${tag.name}</li>
			</#list>
		</ul>
	<#else>
		<p>No tags exist.</p>
	</#if>
	<h2>Articles</h2>
	<#if (articles?size > 0) >
		<ul>
			<#list articles as article>
				<li>${article.name}</li>
			</#list>
		</ul>
	<#else>
		<p>No articles exist.</p>
	</#if>
</body>
</html>