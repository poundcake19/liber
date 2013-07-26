$(document).ready(
	function() {
		$( "button[name='createTag']").click( submitCreateTagForm );
	}
);

function submitCreateTagForm() {
	var form = $( "form[name='createTag']" );
	var name = form.find( "input[name='name']" ).val();
	var parent = form.find( "input[name='parent']" ).val();
	var tag = { name: name, parent: parent };
	$.ajax(
		{
			url: "/liber-services/tags", 
			type: "POST", 
			data: tag, 
			success: displayCreateTagFormSubmissionResult
		}
	);
	return false;
}

function displayCreateTagFormSubmissionResult( tag ) {
	$(".modal-body").prepend( 
			"<div class='alert alert-success'>" + 
			"<button type='button' class='close' data-dismiss='alert'>&times;</button>" + 
			"<p>Tag created successfully! View <a href='" + getSelfLink( tag ) + "'>" + 
			tag.name + "</a></p>" + 
			"</div>" );
}

function getSelfLink( tag ) {
	for( var i = 0; i < tag.links.length; i++ ) {
		if( tag.links[i].relationship == 'self' ) {
			return tag.links[i].url;
		}
	}
}