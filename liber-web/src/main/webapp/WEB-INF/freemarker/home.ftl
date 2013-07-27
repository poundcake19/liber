<#assign title="Home"/>
<#assign scripts = ["scripts/tags.js" ] />
<#include "/includes/header.ftl"/>
	<p>Liber is an awesome web content management system!</p>
</div>
<div class="row-fluid">
	<ul class="breadcrumb" data-bind="foreach: tagHierarchy">
		<li data-bind="visible: $index() == ($root.tagHierarchy().length - 1)">
			<span class="divider">/</span>
			<span data-bind="text: name"></span>
		</li>
		<li data-bind="visible: $index() != ($root.tagHierarchy().length - 1)">
			<span class="divider">/</span>
			<a href="#" data-bind="text: name, click: $root.goToTag"></a>
		</li>
	</ul>
</div>
<div class="row-fluid">
	<div class="span4">
		<div class="well">
			<ul class="nav nav-list" data-bind="visible: tags().length > 0">
				<li class="nav-header"><i class="icon-tags"></i>Tags</li>
				<!-- ko foreach: tags -->
					<li><a href="#" data-bind="text: name, click: $root.goToTag"></a></li>
				<!-- /ko -->
				<li class="divider"></li>
			</ul>
			<p data-bind="visible: tags().length == 0">No tags exist.</p>
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
					<!-- ko foreach: successfulTagAlerts -->
						<div class="alert alert-success">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							<p>Tag created successfully! View 
								<a href="#" data-bind="text: name, click: $root.goToTag"></a>
							</p>
						</div>
					<!-- /ko -->
					<form name="createTag" class="form-inline">
						<input type="text" 
								data-bind="value: tagForm.name"
								placeholder="Tag Name" 
								class="input-medium" />
						<input type="hidden" data-bind="value: tagForm.parent"/>
					</form>
				</div>
				<div class="modal-footer">
					<button class="btn btn-primary" data-bind="click: createTag">
						<i class="icon-tag icon-white"></i>Create Tag
					</button>
				</div>
			</div>
		</div>
	</div>
	<div class="span8">
		<h2>Articles</h2>
		<table class="table table-striped table-hover" data-bind="visible: articles().length > 0">
			<thead>
				<tr>
					<th>Name</th>
				</tr>
			</thead>
			<tbody data-bind="foreach: articles">
				<tr>
					<td data-bind="text: name"></td>
				</tr>
			</tbody>
		</table>
		<p data-bind="visible: articles().length == 0">No articles exist.</p>
		<p>
			<a href="#createArticleModal" role="button" class="btn btn-primary" data-toggle="modal">
				Create Article
			</a>
			<div id="createArticleModal" 
					class="modal hide fade" 
					tabindex="-1" 
					role="dialog" 
					aria-labelledby="createArticleModalLabel" 
					aria-hidden="true">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						x
					</button>
					<h3 id="createArticleModalLabel">Create Article</h3>
				</div>
				<div class="modal-body">
					<!-- ko foreach: successfulTagAlerts -->
						<div class="alert alert-success">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							<p>Article created successfully! View 
								<a href="#" data-bind="text: name, click: $root.goToArticle"></a>
							</p>
						</div>
					<!-- /ko -->
					<form class="form-inline">
						<input type="text" 
								data-bind="value: articleForm.name"
								placeholder="Article Name" 
								class="input-medium" />
						<textarea data-bind="value: articleForm.content"></textarea>
					</form>
				</div>
				<div class="modal-footer">
					<button class="btn btn-primary" data-bind="click: createArticle">
						<i class="icon-tag icon-white"></i>Create Article
					</button>
				</div>
			</div>
		</p>
	</div>
</div>
<#include "/includes/footer.ftl"/>