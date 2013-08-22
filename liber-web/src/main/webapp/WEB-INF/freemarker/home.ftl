			<#assign title="Home"/>
			<#include "/includes/header.ftl"/>
				<p>Liber is an awesome web content management system!</p>
			</div>
			<div class="row-fluid" data-bind="template: 'breadcrumbs'"></div>
			<div class="row-fluid" data-bind="if: isContentView">
				<div class="span4" data-bind="template: 'tagsNav'"></div>
				<!-- ko with: articleViewModel -->
					<div class="span8" data-bind="visible: isHomeView">
						<p>Navigate to a tag on the left to view the associated articles.</p>
					</div>
					<div class="span8" data-bind="visible: isTagListingView, template: 'tagArticlesListing'"></div>
					<div class="span8" 
						data-bind="visible: isViewArticleView, 
									template: { name: 'viewArticle', data: activeArticle() }">
					</div>
					<div class="span8" data-bind="visible: isCreateView, template: 'createArticle'"></div>
				<!-- /ko -->
			</div>
			<div class="row-fluid" 
				data-bind="visible: isCreateFieldView, 
							template: { name: 'createField', data: fieldViewModel }">
			</div>
		</div>
	</div>
</body>
</html>