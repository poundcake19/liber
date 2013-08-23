infuser.defaults.templateUrl = "templates";

function MasterViewModel() {
	var self = this;
	
	self.contentView = "content";
	self.createFieldView = "createField";
	self.view = ko.observable( self.contentView );
	self.isContentView = ko.computed( function() { return self.view() == self.contentView; } );
	self.isCreateFieldView = ko.computed( function() { return self.view() == self.createFieldView; } );
	self.goToCreateFieldView = function() { self.view( self.createFieldView ); };
	self.goToContentView = function() { self.view( self.contentView );	};
	
	self.articleViewModel = new ArticleViewModel( self );
	self.fieldViewModel = new FieldViewModel( self );
	self.tagViewModel = new TagViewModel( self, self.articleViewModel );
	self.tagViewModel.goToTag( { id: 1 } );
}

$(document).ready(
	function() {
		ko.applyBindings( new MasterViewModel() );
	}
);

function buildTagHierarchy( tag ) {
	var hierarchy = [];
	var currentTag = tag;
	while( currentTag != null ) {
		hierarchy.unshift( currentTag );
		currentTag = currentTag.parent;
	}
	return hierarchy;
}

function getLink( object, relationship ) {
	for( var i = 0; i < object.links.length; i++ ) {
		if( object.links[i].relationship == relationship ) {
			return object.links[i].url;
		}
	}
}