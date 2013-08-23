function FieldViewModel( masterViewModel ) {
	var self = this;
	self.masterViewModel = masterViewModel;
	
	self.form = new FieldForm( "" );
	
	self.createField = function() {
		var field = { name: self.form.name() };
		$.ajax(
			{
				url: "/liber-services/fields", 
				type: "POST", 
				data: JSON.stringify( field ), 
				success: function( field ) {
					alert( "success!" );
//						self.successfulCreates.push( article );
				}, 
				contentType: "application/json"
			}
		);
	};
}

function FieldForm( name ) {
	var self = this;
	
	self.name = ko.observable( name );
}