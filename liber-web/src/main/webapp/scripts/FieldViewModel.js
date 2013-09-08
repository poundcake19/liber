function FieldViewModel( masterViewModel ) {
	var self = this;
	self.masterViewModel = masterViewModel;
	self.hierarchicalValueViewModel = new HierarchicalValueViewModel();
	
	self.activeField = ko.observable();
	self.fields = ko.observableArray( [] );
	self.form = new FieldForm( "", "text" );

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
			self.hierarchicalValueViewModel.goToValue( 
					new HierarchicalFieldValue( "_root", null, [] ) );
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
	
}

function HierarchicalValueViewModel( value ) {
	var self = this;
	
	self.activeValue = ko.observable( value );
	self.newValueText = ko.observable();

	self.createHierarchicalValue = function() {
//		var newValue = {
//			value: self.newValueText(), 
//			parent: self.activeValue(), 
//			children: ko.observableArray( [] )
//		};
		var newValue = new HierarchicalFieldValue( self.newValueText(), self.activeValue(), [] );
		var activeValue = self.activeValue();
		activeValue.children.push( newValue );
		self.newValueText( "" );
		alert( "Persistence needs implemented." );
	};
	
	self.goToValue = function( value ) {
		self.activeValue( value );
	};
}

function HierarchicalFieldValue( value, parent, children ) {
	var self = this;
	
	self.value = ko.observable( value );
	self.parent = ko.observable( parent );
	self.children = ko.observableArray( children );
	self.valueHierarchy = ko.observableArray( buildValueHierarchy( this ) );
}

function buildValueHierarchy( value ) {
	var hierarchy = [];
	var currentValue = value;
	while( currentValue != null ) {
		hierarchy.unshift( currentValue );
		currentValue = currentValue.parent();
	}
	return hierarchy;
}