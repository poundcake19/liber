$(document).ready(
	function() {
		tinymce.init( { selector: 'textarea' } );
		$("[name^='tags']").typeahead( { source: tags } );
	}
);

function tags( query, callback ) {
	//TODO make ajax call
	return [ "aaaa", "aabb", "aacc", "bbbb" ];
}