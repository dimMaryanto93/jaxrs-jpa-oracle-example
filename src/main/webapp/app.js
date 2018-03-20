$('h2').text("Halo dunia");

$(document).ready(function() {
	console.log("Jquery works");
	// $('#listenerClick').click(function() {
	// alert("Button click!");
	// });
	//
	// $('#getNasabah').click(function() {
	// loadData();
	// });

	$("form").on("submit", function(event) {
		event.preventDefault();
		var cif = $('#cif').val();
		var namaLengkap = $('#namaLengkap').val();
		var value = {
				"cif" : cif,
				"namaLengkap" : namaLengkap
		}
		$.ajax({
			type : "POST",
			contentType : "application/json; charset=utf-8",
			url : "/jaxrs-example/api/nasabah/save",
			data : JSON.stringify(value),
			success : function() {
				console.log("Data berhasil dikirim ke server!");
			},
			error: function(){
				alert("Tidak dapat menyimpan ke server!");
			},
			dataType : "json"
		});
	});

});

function loadData() {
	$.get("/jaxrs-example/api/nasabah/list", function(data) {
		console.log(data);
	}).done(function() {
		console.log("second success");
	}).fail(function() {
		console.log("error");
	}).always(function() {
		console.log("finished");
	});
}

/* button handler click */
function clickMe() {
	console.log("Button di click!");
}