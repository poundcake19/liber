var tagFields = 0;
var tags = [];

$(document).ready(
	function() {
		tinymce.init( { selector: 'textarea' } );
		loadTagPaths();
		$("button[name='addTag']").click( addTagField );
	}
);

function addTagField() {
	$(".tagFields").append( '<input type="text" name="tags['+tagFields+']" autocomplete="off"><br>' );
	$("[name^='tags']").typeahead( { source: tags } );
	tagFields++;
	return false;
}

function loadTagPaths() {
	$.ajax(
		{
			url: "/liber-services/tags", 
			success: function( data ) { tags = mapTagsToPaths( JSON.parse( data ) ); }
		}
	);
}

function mapTagsToPaths( tags ) {
	return $.map( tags, function( tag ) { return tag.path; } );
}