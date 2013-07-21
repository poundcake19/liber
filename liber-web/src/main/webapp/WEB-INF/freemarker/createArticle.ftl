<#assign title="Create Article"/>
<#assign scripts = ["//tinymce.cachefly.net/4.0/tinymce.min.js", 
					"scripts/createArticle.js" ] />
<#include "/includes/header.ftl"/>
	<form method="post">
		<label for="name">Name:</name>
		<input type="text" name="name" value="${article.name}">
		<textarea name="content" value="${article.content}"></textarea>
		<div class="tagFields"></div>
		<button class="btn btn-primary" name="addTag">Add Tag</button><br>
		<input type="submit" name="save" value="Save" class="btn btn-primary">
	</form>
<#include "/includes/footer.ftl"/>