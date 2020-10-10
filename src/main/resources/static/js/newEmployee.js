
function emailCheck(){
	
    var email = document.getElementById('email');
    var message = document.getElementById('confirmMessage');
	
    var badColor = "#ff6666";
    var goodColor = "#66cc66";
    
    if(email.value != ""){
    	var regMail     =   /^([_a-zA-Z0-9-]+)(\.[_a-zA-Z0-9-]+)*@([a-zA-Z0-9-]+\.)+([a-zA-Z]{2,3})$/;
        if(regMail.test($("#email").val()) == false){
        	email.style.backgroundColor = badColor;
            message.style.color = badColor;
            message.innerHTML = "Email format is invalid!"
                message.style.display = "block";
            return false;
        }else{
        	email.style.backgroundColor = goodColor;
            message.style.display = "none";
        }
    }

	
     