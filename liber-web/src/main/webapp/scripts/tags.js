$(document).ready(
	function() {
		$( "button[name='createTag']").click( submitCreateTagForm );
	}
);

function submitCreateTagForm() {
	$( "form[name='createTag']" ).submit();
}