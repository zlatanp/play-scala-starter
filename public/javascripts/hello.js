if (window.console) {
  console.log("Welcome to your Play application's JavaScript!");
}

function putIn(){
var name = document.getElementById("nameInput").value;
var surname = document.getElementById("surnameInput").value;
var hit = document.getElementById("hitInput").value;

var itsOk = true;

if(name.length <= 0 || name == null || name === undefined){
    itsOk = false;
}else if(surname.length <= 0 || surname == null || surname === undefined){
          itsOk = false;
}else if(hit.length <= 0 || hit == null || hit === undefined){
          itsOk = false;
}

if(!itsOk){
    alert("All inputs must be filled!")
    return false;
}else{
    return true;
}

}
//
$(document).ready(function() {
    $("#tableWithAll").html('<tr><th>Name</th><th>Surname</th><th>Biggest Hit</th><th>Option</th></tr>');
	 $.ajax({
             type: 'GET',
             url: '/getPersons',
             dataType: 'json',
             success: function(data){
             	console.log(data);
             	    var array = data[0];
             	    for(var i=0; i<array.length; i++){
             	    //alert(array[0].length);
             	        $("#tableWithAll").append('<tr><td>' + array[i].name + '</td><td>' + array[i].surname + '</td><td>' + array[i].hit + '</td><td><button>Delete</button> <button>Modify</button></td></tr>');
                    }
                }

     	});
	});


function home(){
    window.location.href = "http://localhost:9000/"
}