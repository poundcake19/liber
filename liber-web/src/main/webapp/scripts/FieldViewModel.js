function FieldViewModel( masterViewModel ) {
	var self = this;
	self.masterViewModel = masterViewModel;
	
	self.activeField = ko.observable();
	self.fields = ko.observableArray( [] );
	self.form = new FieldForm( "", "text" );
	
	self.valueHierarchy = ko.observableArray( buildValueHierarchy() );
	self.activeValue = ko.observable();
	self.newValueText = ko.observable();
	self.createHierarchicalValue = function() {
		var newValue = {
			value: self.newValueText(), 
			parent: self.activeValue(), 
			children: ko.observableArray( [] )
		};
		var activeValue = self.activeValue();
		activeValue.children.push( newValue );
		self.newValueText( "" );
		alert( "Persistence needs implemented." );
	};
	self.goToValue = function( value ) {
		self.activeValue( value );
		self.valueHierarchy( buildValueHierarchy( value ) );
	};

	self.createView = "create";
	self.listingView = "listing";
	self.viewView = "view";
	self.view = ko.observable( self.listingView );
	self.isCreateView = ko.computed( 
			function() {
				return self.view() == self.createView;
			}
		);
	self.isListingView = ko.computed(
		function() {
			return self.view() == self.listingView;
		}
	);
	self.isViewView = ko.computed(
		function() {
			return self.view() == self.viewView;
		}
	);
	self.goToCreateView = function() {
		self.view( self.createView );
	};
	self.goToListingView = function() {
		self.view( self.listingView );
	};
	self.goToViewView = function( field ) {
		self.activeField( field );
		if( field.type == 'hierarchical' ) {
			alert( "Auto load option from db" );
			self.activeValue( { value: "_root", parent: null, children: ko.observableArray( [] ) } );
			self.valueHierarchy( buildValueHierarchy( self.activeValue() ) );
		}
		self.view( self.viewView );
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
					self.fields.push( field );
				}, 
				contentType: "application/json"
			}
		);
	};
	self.deleteField = function() {
		alert( "Needs implemented" );
	};
	
	$.getJSON( "/liber-services/fields", function( fields ) { self.fields( fields ); } );
}

function FieldForm( name, type ) {
	var self = this;
	
	self.name = ko.observable( name );
	self.type = ko.observable( type );
	self.values = ko.observableArray( [] );
	
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