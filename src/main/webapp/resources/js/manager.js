/**
 * 
 */


/*	function selectFunction(){
		amount = document.getElementById("amount").innerHTML;
		alert("Amount: " + amount);
		delete amount;
	};*/
	
	
function rowSetup(){
	var table = document.getElementById("empTable");
	var rows = document.getElementsByTagName("tr");
	
	for(i = 0 ; i < rows.length; i++){
		var currentRow = table.rows[i];
		var createClickHandler =
			function(row){
			return function(){
				var cell = row.getElementsByTagName("td")[7];
				var id = cell.innerHTML;
				//alert("id:" + id)
			}
		}
		currentRow.onclick = createClickHandler(currentRow);
	}
};

function updateReimbursements(){
	var table = document.getElementById("empTable");
	var rows = document.getElementsByTagName("tr");

	var declinedList = [];
	var approvedList = [];

	for(var i = 1; i < rows.length; i ++){
		var row = table.rows[i];
		
		var status = row.cells[5].getElementsByTagName("select")[0];
		var sValue = status.value;
		
		var cell = row.getElementsByTagName("td")[7];
		var value = cell.innerHTML;
		
		
		if(sValue === "APPROVE"){
			approvedList.push(value);
		}else if(sValue ==="DECLINE"){
			declinedList.push(value);
		}
		//pending?
	}

	var listData = JSON.stringify({
		aList : approvedList,
		dList : declinedList
	});
	
	$.ajax({
		url: "updateRmbmt.do",
		type: "POST",
		contentType: "application/json",
		data : listData,
		success:function(){
			location.reload();
		}
	});

};

function testAJAX(){
	$.ajax({	
		url: "empList",
		method: "POST",
		success: function(result, status, xhr){
			console.log(result + " " + status);
		},
		error: function(xhr, status){
			console.log("error");
		},
		complete: function(shr, status){
			console.log("complete!");
		}
	});
}

$(document).ready(function() { 
	var allOpt = document.createElement("option");
	allOpt.innerHTML = "All";
	document.getElementById("selectEmployee").appendChild(allOpt);
	$.ajax({	
		url: "empList",
		method: "POST",
		dataType: "json",
		contentType: "application/json",
		success: function(result, status, xhr){
			console.log(result.userList.length);
			for(var i = 0 ; i < result.userList.length; i++){
				var user = result.userList[i];
				var nameDisplay = user.firstName + " " + user.lastName;
				
				var opt = document.createElement("option")
				opt.innerHTML = nameDisplay;
				document.getElementById("selectEmployee").appendChild(opt);
			}
		}
	});
});


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

window.onload = rowSetup();