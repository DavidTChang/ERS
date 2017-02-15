/**
 * 
 */


function reloadPage() {
    location.reload();
};


var rows = $('#empTable tr');

function filterStatus() {
	
	console.log("clicked");
	
    var status = document.getElementById("filterStatusId").value;
	if(status === "Pending"){
		var pending = rows.filter('.active').show();
	    rows.not( pending ).hide();
	}else if(status === "Approved"){
		var success = rows.filter('.success').show();
	    rows.not( success ).hide();
	}else if(status ==="Declined"){
		var danger = rows.filter('.danger').show();
	    rows.not( danger ).hide();
	}else if(status ==="All"){
	    location.reload();
	}
    var header = rows.filter('.header').show();
}

function filterStatusRevamped(){
	var rows = $('#empTable tr');
	
}