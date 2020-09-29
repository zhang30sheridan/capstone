function verify() { 
	var password1 = document.forms['form']['password'].value; 
	var password2 = document.forms['form']['verifyPassword'].value; 
	if (password1 == null || password1 == "" || password1 != password2) { 
		document.getElementById("passworderror").style.display = "none";
		document.getElementById("passworderror").innerHTML= "Please check your passwords"; 
		return false; 
	} 
}


function emailCheck(){
    if($("#email").val()==""){
        $("#email").addClass('is-invalid');
        return false;
    }else{
        var regMail     =   /^([_a-zA-Z0-9-]+)(\.[_a-zA-Z0-9-]+)*@([a-zA-Z0-9-]+\.)+([a-zA-Z]{2,3})$/;
        if(regMail.test($("#email").val()) == false){
            $("#email").addClass('is-invalid');
            return false;
        }else{
            $("#email").removeClass('is-invalid');
            document.getElementById("next-form").classList.remove("collapse");
            document.getElementById("next-form").classList.add("show");
        }

    }
}

function checkPass()
{
    var pass1 = document.getElementById('password');
    var pass2 = document.getElementById('verifyPassword');
    var message = document.getElementById('confirmMessage');

    var goodColor = "#66cc66";
    var badColor = "#ff6666";
    
    if(pass1.value == pass2.value){
        //The passwords match. 
        //Set the color to the good color and inform
        //the user that they have entered the correct password 
        pass2.style.backgroundColor = goodColor;
        message.style.color = goodColor;
        message.innerHTML = "Passwords Match"
    }else{
        pass2.style.backgroundColor = badColor;
        message.style.color = badColor;
        message.innerHTML = "Passwords Do Not Match!"
    }
} 


	
     