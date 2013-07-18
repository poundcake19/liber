<#assign title="Tag - ${tag.name}"/>
<#include "/includes/header.ftl"/>
	<h2>Tags</h2>
	<#if (tag.childTags?size > 0) >
		<ul>
			<#list tag.childTags as tag>
				<li><a href="<@spring.url "/tags/${tag.id}"/>">${tag.name}</a></li>
			</#list>
		</ul>
	<#else>
		<p>No tags exist.</p>
	</#if>
	<form action="<@spring.url "/createTag"/>" method="post">
		<label for="name">Name</label>
		<input type="text" name="name"/>
		<input type="hidden" name="parent" value="${tag.id}"/>
		<input type="submit" name="save" value="Save"/>
	</form>
	<h2>Articles</h2>
	<#if (tag.articles?size > 0) >
		<ul>
			<#list tag.articles as article>
				<li>${article.articleName}</li>
			</#list>
		</ul>
	<#else>
		<p>No articles exist.</p>
	</#if>
<#include "/includes/footer.ftl"/>