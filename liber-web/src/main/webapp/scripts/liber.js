infuser.defaults.templateUrl = "templates";

function TagForm( name, parent, path ) {
	var self = this;
	self.name = ko.observable( name );
	self.parent = ko.observable( parent );
	self.path = ko.observable( path );
}

function ArticleForm( name, content, tags ) {
	var self = this;
	self.name = ko.observable( name );
	self.content = ko.observable( content );
	self.tags = ko.observableArray( tags );
	self.fields = ko.observableArray( [] );
}

function ArticleViewModel( masterViewModel ) {
	var self = this;
	self.masterViewModel = masterViewModel;
	
	self.articles = ko.observableArray( [] );
	self.articleForm = new ArticleForm( "", "", [] );
	self.tagPaths = [];
	self.fields = ko.observableArray( [] );
	
	self.successfulCreates = ko.observableArray( [] );
	self.successfulDeletes = ko.observableArray( [] );
	
	self.homeView = "home";
	self.tagListingView = "tagListing";
	self.viewArticleView = "view";
	self.createView = "create";
	self.activeArticle = ko.observable( { name: "", content: "", fields: [] } );
	self.articleView = ko.observable( self.homeView );
	self.isHomeView = ko.computed( function() {
			return self.articleView() == self.homeView;
		}
	);
	self.isTagListingView = ko.computed( function() {
			return self.articleView() == self.tagListingView;
		}
	);
	self.isViewArticleView = ko.computed( function() {
			return self.articleView() == self.viewArticleView;
		}
	);
	self.isCreateView = ko.computed( function() {
			return self.articleView() == self.createView;
		}
	);
	self.goToHomeArticles = function() {
		self.articleView( self.homeView );
	};
	self.goToTagListing = function () {
		self.successfulCreates.removeAll();
		self.successfulDeletes.removeAll();
		self.articleView( self.tagListingView );
		self.articleForm.name( "" );
		self.articleForm.content( "" );
		self.articleForm.tags( [] );
	};
	self.goToViewArticle = function( article ) {
		$.getJSON( "/liber-services/articles/" + article.id, 
					function( article ) {
						self.activeArticle( article );
					} );
		self.articleView( self.viewArticleView );
	};
	self.goToCreateArticle = function() {
		$.getJSON( "/liber-services/tags", 
					function( tags ) { 
						self.tagPaths = $.map( tags, 
												function( tag ) { return tag.path; } ); 
					} );
		self.articleView( self.createView );
	};
	
	self.createArticle = function() {
		var article = { 
			name: self.articleForm.name(), 
			content: self.articleForm.content(), 
			tags: $.map( self.articleForm.tags(), function( tag ) { return tag.path; } ),
			fields: self.articleForm.fields()
		};
		$.ajax(
			{
				url: "/liber-services/articles", 
				type: "POST", 
				data: JSON.stringify( article ), 
				success: function( article ) {
					self.articles.push( article );
					self.goToTagListing();
					self.successfulCreates.push( article );
				}, 
				contentType: "application/json"
			}
		);
	};
	
	self.deleteArticle = function( article ) {
		$.ajax(
			{
				url: "/liber-services/articles/" + article.id, 
				type: "DELETE", 
				success: function() {
					self.goToTagListing();
					self.articles.remove( 
							function( item ) { return item.id == self.activeArticle().id; } );
					self.successfulDeletes.push( self.activeArticle() );
					self.activeArticle( { name: "", content: "" } );
				}
			}
		);
	};
	
	self.addTag = function() {
		self.articleForm.tags.push( { path: "" } );
	};
	
	$.getJSON( "/liber-services/fields", 
				function( fields ) { 
					self.fields( fields );
					self.articleForm.fields( $.map( fields, 
													function( field ) { 
														return { 
																	id: field.id, 
																	name: field.name, 
																	value: "" 
																};
													}
											)
					);
				}
	);
}

function TagViewModel() {
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
	
	self.tagForm = new TagForm( "", null );
	self.tags = ko.observableArray( [] );
	self.successfulTagAlerts = ko.observableArray( [] );
	
	self.chosenTag = ko.observable();
	self.tagHierarchy = ko.observableArray( buildTagHierarchy() );
	
	self.goToTag = function( tag ) {
		if( !self.articleViewModel.isCreateView() || 
				confirm( "Navigating away will lose any work on the current article. " +
						"Do you want to continue?" ) ) {
			var url = "/liber-services/tags/" + tag.id;
			var articlesUrl = "/liber-services/tags/" + tag.id + "/articles";
			$.getJSON( url, 
						function( tag ) { 
							self.tags( tag.childTags );
							self.tagHierarchy( buildTagHierarchy( tag ) );
							self.tagForm.parent( tag.id );
							$.getJSON( articlesUrl, self.articleViewModel.articles );
						} );
			if( tag.id == 1 ) {
				self.articleViewModel.goToHomeArticles();
			}
			else {
				self.articleViewModel.goToTagListing();
			}
		}
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
	
	self.goToTag( { id: 1 } );
}

$(document).ready(
	function() {
		ko.applyBindings( new TagViewModel() );
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