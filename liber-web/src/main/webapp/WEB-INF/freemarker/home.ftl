<#assign title="Home"/>
<#assign scripts = ["scripts/tags.js" ] />
<#include "/includes/header.ftl"/>
	<p>Liber is an awesome web content management system!</p>
</div>
<div class="row-fluid">
	<div class="span4">
		<div class="well">
			<#if (tags?size > 0) >
				<ul class="nav nav-list">
					<li class="nav-header"><i class="icon-tags"></i>Tags</li>
					<#list tags as tag>
						<li><a href="<@spring.url "/tags/${tag.id}"/>">${tag.name}</a></li>
					</#list>
					<li class="divider"></li>
				</ul>
			<#else>
				<p>No tags exist.</p>
			</#if>
			<a href="#myModal" role="button" class="btn btn-primary" data-toggle="modal">
				Create Tag
			</a>
			<div id="myModal" 
					class="modal hide fade" 
					tabindex="-1" 
					role="dialog" 
					aria-labelledby="myModalLabel" 
					aria-hidden="true">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						x
					</button>
					<h3 id="myModalLabel">Create Tag</h3>
				</div>
				<div class="modal-body">
					<form name="createTag" 
							action="<@spring.url "/createTag"/>" 
							method="post" 
							class="form-inline">
						<input type="text" 
								name="name" 
								placeholder="Tag Name" 
								class="input-medium" />
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
		<#if (articles?size > 0) >
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>Name</th>
					</tr>
				</thead>
				<tbody>
					<#list articles as article>
						<tr>
							<td>${article.articleName}</td>
						</tr>
					</#list>
				</tbody>
			</table>
		<#else>
			<p>No articles exist.</p>
		</#if>
<#include "/includes/footer.ftl"/>