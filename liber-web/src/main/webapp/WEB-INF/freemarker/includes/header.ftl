<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Liber - ${title}</title>
	<link href="<@spring.url "/bootstrap/bootstrap.min.css"/>" rel="stylesheet"/>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
	<script src="<@spring.url "/bootstrap/bootstrap.min.js"/>"></script>
	<script src="tinymce/tinymce.min.js"></script>
	<script src="tinymce/jquery.tinymce.min.js"></script>
	<script src="<@spring.url "/scripts/knockout-2.3.0.js"/>"></script>
	<script src="scripts/ko-bootstrap-typeahead.js"></script>
	<script src="scripts/TrafficCop-0.3.0.js"></script>
	<script src="scripts/infuser-0.2.0.js"></script>
	<script src="scripts/koExternalTemplateEngine-2.0.5.min.js"></script>
	<script src="scripts/liber.js"></script>
	<script src="scripts/field.js"></script>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<h1>${title}</h1>
		</div>
		<div class="row-fluid">
			<div class="navbar">
				<div class="navbar-inner">
					<ul class="nav">
						<li><a href="<@spring.url "/"/>"><i class="icon-home"></i>Home</a></li>
						<li>
							<a href="<@spring.url "/createArticle"/>">
								<i class="icon-file"></i>Create Article
							</a>
						</li>
						<li>
							<a href="#" data-bind="click: goToCreateFieldView">
								<i class="icon-th-list"></i>Create Field
							</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="row-fluid">