<#assign title="Create Article"/>
<#assign scripts = ["//ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js", 
					"//tinymce.cachefly.net/4.0/tinymce.min.js", 
					"scripts/createArticle.js" ] />
<#include "/includes/header.ftl"/>
	<form method="post">
		<input type="text" name="name" value="${article.name}" />
		<textarea name="content" value="${article.content}"></textarea>
		<input type="submit" name="save" value="Save"/>
	</form>
<#include "/includes/footer.ftl"/>