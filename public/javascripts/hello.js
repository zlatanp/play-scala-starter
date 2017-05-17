if (window.console) {
  console.log("Welcome to your Play application's JavaScript!");
}

function putIn(){
var name = document.getElementById("name").value;
var surname = document.getElementById("surname").value;
var hit = document.getElementById("hit").value;

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
             	        $("#tableWithAll").append('<tr><td>' + array[i].name + '</td><td>' + array[i].surname + '</td><td>' + array[i].hit + '</td><td><button onclick= "deleteOne(\'' +   array[i].name + '\')">Delete</button> <button onclick="modifyOne(\'' +   array[i].name + '\', \'' +   array[i].surname + '\', \'' +   array[i].hit + '\')">Modify</button></td></tr>');
                    }
                }

     	});
	});


function home(){
    window.location.href = "http://localhost:9000/"
}

function deleteOne(name){

    $.ajax({
        type: 'DELETE',
        url: '/delete/' + name,
        dataType: 'json',
        success: function(data){
            alert(name + " DELETED!");
            location.reload();
        }
    });
}

function modifyOne(name, surname, hit){
    $("#dialog").dialog({
        autoOpen: false,
        show: {
            effect: "blind",
            duration: 500
        },
        hide: {
            effect: "explode",
            duration: 500
        }
    });

    $("#dialog").dialog("open");

    $("#Dname").val(name);
    $("#Dsurname").val(surname);
    $("#Dhit").val(hit);
    //alert(name + " " + surname + " " + hit);

    $('#dialogModify').on('click', function () {
        var newName = $("#Dname").val();
        var newSurname = $("#Dsurname").val();
        var newHit = $("#Dhit").val();

        $.ajax({
                type: 'PUT',
                url: '/modify/' + name + '/' + newName +'/' + newSurname + '/' + newHit,
                dataType: 'json',
                success: function(data){
                    alert(name + " UPDATED!");
                    location.reload();
                }
            });

        $("#dialog").dialog('close');
    });
}