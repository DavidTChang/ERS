/**
 * 
 */

function updateProfile(){
	$.ajax({
		url: "updateProfile.do",
		method:"POST",
		success: function(result,status,xhr){
			console.log("success");
			document.getElementById("stuff").innerHTML = result.name;
		},
		error: function(xhr, status){
			console.log("error");
		},
		complete:function(xhr, status){
			console.log("complete");
		}
	})
}
document.getElementById("submitProfile").addEventListener("click", updateProfile, false);