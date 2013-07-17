<html>
<head>
	<title>Liber - Create Article</title>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
	<script src="//tinymce.cachefly.net/4.0/tinymce.min.js"></script>
	<script>
		$(document).ready(
			function() {
				tinymce.init( { selector: 'textarea' } );
			}
		);
	</script>
</head>
<body>
	<h1>Create Article</h1>
	<form method="post">
		<textarea name="content"></textarea>
		<input type="submit" name="save" value="Save"/>
	</form>
</body>
</html>