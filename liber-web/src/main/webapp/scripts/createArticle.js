$(document).ready(
	function() {
		tinymce.init( { selector: 'textarea' } );
		$("[name^='tags']").typeahead( { source: retrieveTagPaths() } );
	}
);

function retrieveTagPaths( query, callback ) {
	var tags = [];
	$.ajax(
		{
			url: "/liber-services/tags", 
			async: false, 
			success: function( data ) { tags = mapTagsToPaths( JSON.parse( data ) ); }
		}
	);
	return tags;
}

function mapTagsToPaths( tags ) {
	return $.map( tags, function( tag ) { return tag.path; } );
}