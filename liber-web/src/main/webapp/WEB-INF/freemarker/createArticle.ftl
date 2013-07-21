<#assign title="Create Article"/>
<#assign scripts = ["//tinymce.cachefly.net/4.0/tinymce.min.js", 
					"scripts/createArticle.js" ] />
<#include "/includes/header.ftl"/>
	<form method="post">
		<label for="name">Name:</name>
		<input type="text" name="name" value="${article.name}" />
		<textarea name="content" value="${article.content}"></textarea>
		<input type="text" name="tags[1]"/>
		<input type="text" name="tags[2]"/>
		<input type="text" name="tags[3]"/>
		<input type="submit" name="save" value="Save"/>
	</form>
<#include "/includes/footer.ftl"/>