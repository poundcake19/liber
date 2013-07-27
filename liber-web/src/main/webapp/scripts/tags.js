function TagViewModel() {
	var self = this;
	self.tags = ko.observableArray( [] );
	
	self.goToTag = function( tag ) {
		alert( tag.name );
	};
	
	$.getJSON( "/liber-services/tags?parent=", function( data ) { self.tags( data ); } );	
}


$(document).ready(
	function() {
		ko.applyBindings( new TagViewModel() );
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
			"<p>Tag created successfully! View <a href='" + getLink( tag, 'view' ) + "'>" + 
			tag.name + "</a></p>" + 
			"</div>" );
}

function getLink( object, relationship ) {
	for( var i = 0; i < object.links.length; i++ ) {
		if( object.links[i].relationship == relationship ) {
			return object.links[i].url;
		}
	}
}