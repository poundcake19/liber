<#assign title="Tag - ${tag.name}"/>
<#include "/includes/header.ftl"/>
<div class="span4">
	<div class="well">
		<#if (tag.childTags?size > 0) >
			<ul class="nav nav-list">
				<li class="nav-header"><i class="icon-tags"></i>Tags</li>
				<#list tag.childTags as tag>
					<li><a href="<@spring.url "/tags/${tag.id}"/>">${tag.name}</a></li>
				</#list>
				<li class="divider"></li>
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