function TagForm( name, parent ) {
	var self = this;
	self.name = ko.observable( name );
	self.parent = ko.observable( parent );
}

function TagViewModel() {
	var self = this;
	
	self.tagForm = new TagForm( "", null );
	self.tags = ko.observableArray( [] );
	self.successfulTagAlerts = ko.observableArray( [] );
	self.articles = ko.observableArray( [] );
	
	self.chosenTag = ko.observable();
	
	self.goToTag = function( tag ) {
		$.getJSON( "/liber-services/tags/" + tag.id, 
					function( tag ) { self.tags( tag.childTags ); } );
	};
	
	self.createTag = function() {
		var tag = { name: self.tagForm.name(), parent: self.tagForm.parent() };
		$.ajax(
			{
				url: "/liber-services/tags", 
				type: "POST", 
				data: tag, 
				success: function( tag ) { 
					self.tags.push( tag );
					self.successfulTagAlerts.push( tag );
				}
			}
		);
	};
	
	$.getJSON( "/liber-services/tags?parent=", self.tags );
	$.getJSON( "/liber-services/tags/articles", self.articles );
}


$(document).ready(
	function() {
		ko.applyBindings( new TagViewModel() );
	}
);

function getLink( object, relationship ) {
	for( var i = 0; i < object.links.length; i++ ) {
		if( object.links[i].relationship == relationship ) {
			return object.links[i].url;
		}
	}
}