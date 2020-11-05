//$('document').ready(function(){
//	
//	$('.table .btn-secondary').on('click', function(event){
//		
//		event.preventDefault();
//		
//		var href = $(this).attr('href');
//		
//		$.get(href, function(employee, status){
//			$('#nameEdit').val(employee.name)
//		});
//		
//		$('#editModal').modal();
//	});
//});

function openEditModal(id){
	
	$.ajax({
		url:"/admin/editEmployee/" + id,
		success: function(data){
			$("#holder").html(data);
			$("#editModal").modal("show");
		}
	});
}

function openDeleteModal(id){
	
	$.ajax({
		url:"/admin/deleteEmployee/" + id,
		success: function(data){
			$("#holder").html(data);
			$("#deleteModal").modal("show");
		}
	});
}

