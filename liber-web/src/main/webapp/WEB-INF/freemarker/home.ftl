<#assign title="Home"/>
<#include "/includes/header.ftl"/>
	<p>Liber is an awesome web content management system!</p>
</div>
<div class="row-fluid" data-bind="template: 'breadcrumbs'"></div>
<div class="row-fluid">
	<div class="span4" data-bind="template: 'tagsNav'"></div>
	<!-- ko with: articleViewModel -->
		<div class="span8" data-bind="visible: isHomeView">
			<p>Navigate to a tag on the left to view the associated articles.</p>
		</div>
		<div class="span8" data-bind="visible: isTagListingView">
			<h2>Articles</h2>
			<!-- ko foreach: successfulCreates -->
				<div class="alert alert-success">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					<p>Successfully created 
						<a href="#" data-bind="text: name, click: $parent.goToViewArticle"></a>
					</p>
				</div>
			<!-- /ko -->
			<!-- ko foreach: successfulDeletes -->
				<div class="alert alert-success">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					<p>Successfully deleted <span data-bind="text:name"></span></p>
				</div>
			<!-- /ko -->
			<table class="table table-striped table-hover" data-bind="visible: articles().length > 0">
				<thead>
					<tr>
						<th>Name</th>
					</tr>
				</thead>
				<tbody data-bind="foreach: articles">
					<tr>
						<td data-bind="text: name, click: $parent.goToViewArticle"></td>
					</tr>
				</tbody>
			</table>
			<p data-bind="visible: articles().length == 0">No articles exist.</p>
			<p>
				<button class="btn btn-primary" data-bind="click: goToCreateArticle">
					Create Article
				</button>
			</p>
		</div>
		<div class="span8" data-bind="visible: isViewArticleView, with: activeArticle()">
			<h2 data-bind="text: name"></h2>
			<div class="well" data-bind="html: content"></div>
			<p data-bind="foreach: fields">
				<span data-bind="text: name"></span>: <span data-bind="text: value"></span><br>
			</p>
			<div class="row-fluid">
				<button class="btn btn-warning" data-bind="click: $parent.goToTagListing">Close</button>
				<button class="btn btn-danger" data-bind="click: $parent.deleteArticle">
					<i class="icon-file icon-white"></i>Delete Article
				</button>
			</div>
		</div>
		<div class="span8" data-bind="visible: isCreateView">
			<form class="form-horizontal">
				<p>
					<label for="name">Article Name</label>
					<input type="text" 
							name="name" 
							data-bind="value: articleForm.name"
							placeholder="Article Name" 
							class="input-medium" />
				</p>
				<p>
					<textarea class="tinymce" data-bind="tinymce: articleForm.content"></textarea>
				</p>
				<div id="articleTags" data-bind="foreach: articleForm.tags">
						<input type="text" 
								data-bind="typeahead: $parent.tagPaths, value: path" 
								placeholder="Tag Path"/><br>
				</div>
				<p>
					<button class="btn btn-info" data-bind="click: addTag">
						<i class="icon-tag icon-white"></i>Add Tag
					</button>
				</p>
				<div class="row-fluid" data-bind="foreach: articleForm.fields">
					<p>
						<label data-bind="text: name"></label>
						<input type="text" class="input-medium" data-bind="value: value"/><br>
					</p>
				</div>
				<p>
					<button class="btn btn-primary" data-bind="click: createArticle">
						<i class="icon-file icon-white"></i>Create Article
					</button>
					<button class="btn btn-danger" data-bind="click: goToTagListing">
						Cancel
					</button>
				</p>
			</form>
		</div>
	<!-- /ko -->
</div>
<#include "/includes/footer.ftl"/>