<#assign title="Create Tag"/>
<#include "/includes/header.ftl"/>
	<form method="post">
		<label for="name">Name:</label>
		<input type="text" name="name" value="${tag.name}"/>
		<input type="submit" name="save" value="Save"/>
	</form>
<#include "/includes/footer.ftl"/>