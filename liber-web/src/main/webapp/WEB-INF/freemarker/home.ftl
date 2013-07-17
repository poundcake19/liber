<#assign title="Home"/>
<#include "/includes/header.ftl"/>
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
<#include "/includes/footer.ftl"/>