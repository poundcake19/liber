<#assign title="Create Article"/>
<#assign scripts = ["//tinymce.cachefly.net/4.0/tinymce.min.js", 
					"scripts/createArticle.js" ] />
<#include "/includes/header.ftl"/>
	<form method="post">
		<div class="row-fluid">
			<label for="name">Name:</name>
			<input type="text" name="name" value="${article.name}">
		</div>
		<div class="row-fluid">
			<div class="span8">
				<p>
					<textarea name="content" value="${article.content}"></textarea>
				</p>
			</div>
			<div class="span4">
				<div class="tagFields"></div>
				<button class="btn btn-success" name="addTag">Add Tag</button>
			</div>
		</div>
		<div class="row-fluid">
			<p>
				<input type="submit" name="save" value="Save" class="btn btn-primary">
			</p>
		</div>
	</form>
<#include "/includes/footer.ftl"/>