<#assign title="${tag.name}"/>
<#assign scripts = ["../scripts/tags.js" ] />
<#include "/includes/header.ftl"/>
	<ul class="breadcrumb">
		<li><span class="divider">/</span><a href="<@spring.url "/"/>">Home</a></li>
		<#assign parentsList = [] />
		<#assign currentTag = tag />
		<#list 1..1000 as count>
			<#if !currentTag.parent?? >
				<#break/>
			</#if>
			<#assign parentsList = [currentTag] + parentsList />
			<#assign currentTag = currentTag.parent />
		</#list>
		<#assign parentsList = [currentTag] + parentsList />
		<#list parentsList[0..(parentsList?size - 2)] as node >
			<li>
				<span class="divider">/</span>
				<a href="<@spring.url "/tags/${node.id}"/>">${node.name}</a>
			</li>
		</#list>
		<li><span class="divider">/</span>${tag.name}</li>
	</ul>
</div>
<div class="row-fluid">
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
			<a href="#myModal" role="button" class="btn btn-primary" data-toggle="modal">Create Tag</a>
			<div id="myModal" 
					class="modal hide fade" 
					tabindex="-1" 
					role="dialog" 
					aria-labelledby="myModalLabel" 
					aria-hidden="true">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
					<h3 id="myModalLabel">Create Tag</h3>
				</div>
				<div class="modal-body">
					<form name="createTag" 
							action="<@spring.url "/createTag"/>" 
							method="post" 
							class="form-inline">
						<input type="text" name="name" placeholder="Tag Name" class="input-medium" />
						<input type="hidden" name="parent" value="${tag.id}"/>
					</form>
				</div>
				<div class="modal-footer">
					<button class="btn btn-primary" name="createTag">
						<i class="icon-tag icon-white"></i>Create Tag
					</button>
				</div>
			</div>
		</div>
	</div>
	<div class="span8">
		<h2>Articles</h2>
		<#if (tag.articles?size > 0) >
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>Name</th>
					</tr>
				</thead>
				<tbody>
					<#list tag.articles as article>
						<tr>
							<td>${article.articleName}</td>
						</tr>
					</#list>
				</tbody>
			</table>
		<#else>
			<p>No articles exist.</p>
		</#if>
	</div>
<#include "/includes/footer.ftl"/>