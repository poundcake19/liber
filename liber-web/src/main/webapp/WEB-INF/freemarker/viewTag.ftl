<#assign title="Tag - ${tag.name}"/>
<#include "/includes/header.ftl"/>
<div class="span4">
	<h2><i class="icon-tag"></i>Tags</h2>
	<#if (tag.childTags?size > 0) >
		<ul>
			<#list tag.childTags as tag>
				<li><a href="<@spring.url "/tags/${tag.id}"/>">${tag.name}</a></li>
			</#list>
		</ul>
	<#else>
		<p>No tags exist.</p>
	</#if>
	<form action="<@spring.url "/createTag"/>" method="post" class="form-inline">
		<input type="text" name="name" placeholder="Tag Name" class="input-medium" />
		<input type="hidden" name="parent" value="${tag.id}"/>
		<button class="btn btn-primary">
			<i class="icon-tag icon-white"></i>Create Tag
		</button>
	</form>
</div>
<div class="span8">
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