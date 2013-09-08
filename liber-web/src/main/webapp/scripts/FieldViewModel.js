function FieldViewModel( masterViewModel ) {
	var self = this;
	self.masterViewModel = masterViewModel;
	
	self.fields = ko.observableArray( [] );
	self.form = new FieldForm( "", "text" );
	
	self.createView = "create";
	self.listingView = "listing";
	self.view = ko.observable( self.listingView );
	self.isCreateView = ko.computed( 
		function() {
			var view = self.view();
			return view == self.createView;
		}
	);
	self.isListingView = ko.computed(
		function() {
			return self.view() == self.listingView;
		}
	);
	self.goToCreateView = function() {
		self.view( self.createView );
	};
	self.goToListingView = function() {
		self.view( self.listingView );
	};
	
	self.createField = function() {
//		var field = { name: self.form.name(), type: self.form.type() };
		
		$.ajax(
			{
				url: "/liber-services/fields", 
				type: "POST", 
				data: JSON.stringify( ko.toJS( self.form ) ), 
				success: function( field ) {
					alert( "success!" );
//						self.successfulCreates.push( article );
				}, 
				contentType: "application/json"
			}
		);
	};
	
	$.getJSON( "/liber-services/fields", function( fields ) { self.fields( fields ); } );
}

function FieldForm( name, type ) {
	var self = this;
	
	self.name = ko.observable( name );
	self.type = ko.observable( type );
	self.values = ko.observableArray( [] );
	
//	self.activeHierarchicalValue = ko.observable( 
//		{ 
//			value: "_root", 
//			parent: null, 
//			children: ko.observableArray( [] ) 
//		}
//	);
//	self.hierarchicalValue = ko.observable( "" );
//	self.valueHierarchy = ko.observableArray( buildValueHierarchy( self.activeHierarchicalValue() ) );
	
	self.addValue = function() {
		self.values.push( { value: "" } );
	};
	self.removeValue = function( value ) {
		self.values.remove( value );
	};
	
	self.addHierarchicalValue = function() {
		var newValue = {
			value: self.hierarchicalValue(), 
			parent: self.activeHierarchicalValue(), 
			children: ko.observableArray( [] )
		};
		var activeValue = self.activeHierarchicalValue();
		activeValue.children.push( newValue );
		self.hierarchicalValue( "" );
	};
	self.goToValue = function( value ) {
		self.valueHierarchy( buildValueHierarchy( value ) );
		self.activeHierarchicalValue( value );
	};
	
}

function buildValueHierarchy( value ) {
	var hierarchy = [];
	var currentValue = value;
	while( currentValue != null ) {
		hierarchy.unshift( currentValue );
		currentValue = currentValue.parent;
	}
	return hierarchy;
}