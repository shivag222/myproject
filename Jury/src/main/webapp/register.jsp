<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
<title>Jury Charge Selection System</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript">
  function validateForm() {
    var x = document.forms["myForm"]["fname"].value;
    if (x == null || x == "") {
        alert("First Name Should not be empty");
        return false;
    }
    var x = document.forms["myForm"]["lname"].value;
    if (x == null || x == "") {
        alert("Last Name Should not be empty");
        return false;
    }
    
    var x = document.forms["myForm"]["username"].value;
    if (x == null || x == "") {
        alert("User Id Should not be empty");
        return false;
    }
    var x = document.forms["myForm"]["password"].value;
    if (x == null || x == "") {
        alert("Password Should not be empty");
        return false;
    }
    var x = document.forms["myForm"]["repwd"].value;
    if (x == null || x == "") {
        alert("Password Should not be empty");
        return false;
    }
    var x = document.forms["myForm"]["email"].value;
    if (x == null || x == "") {
        alert("Email Id Should not be empty");
        return false;
    }else{
      var at = x.indexOf("@");
      var dot = x.lastIndexOf(".");
      if (at<1 || dot<at+2 || dot+2>=x.length) {
          alert("Please enter valid e-mail address");
          return false;
      }
    }
    var x = document.forms["myForm"]["phone"].value;
    if (x == null || x == "") {
        alert("Phone Number Should not be empty");
        return false;
    }else{
      if(isNaN(x)){
        alert("Enter Valid Phone Number");
        return false;
      }
    }
    var x = document.forms["myForm"]["address"].value;
    if (x == null || x == "") {
        alert("Address Should not be empty");
        return false;
    }
    var x = document.forms["myForm"]["city"].value;
    if (x == null || x == "") {
        alert("City Should not be empty");
        return false;
    }
    var x = document.forms["myForm"]["securityQuestion1"];
    x = x.options[x.selectedIndex].value;
    if (x == null || x == "") {
        alert("please select Security Question1");
        return false;
    }
    var x = document.forms["myForm"]["sa1"].value;
    if (x == null || x == "") {
        alert("please answer Security Question1");
        return false;
    }
    var x = document.forms["myForm"]["securityQuestion2"];
    x = x.options[x.selectedIndex].value;
    if (x == null || x == "") {
    	alert("please select Security Question2");
        return false;
    }
    var x = document.forms["myForm"]["sa2"].value;
    if (x == null || x == "") {
    	alert("please answer Security Question2");
        return false;
    }
    var error = document.getElementById("error").innerHTML;
    if (error == null || error != "") {
    	alert("please give other user id");
        return false;
    }
    alert( document.getElementById("othergend").style.display);
    if( document.getElementById("othergend").style.display != "none"){
	    var other = document.getElementById("othergend").value;
	    if (other == null || other == "") {
	    	alert("please give gender");
	        return false;
	    }else{
	    	alert(other);
	    	 document.getElementById("other").value = other;
	    	alert( document.getElementById("other").value);
	    }
    }
  }
  
  function checkUserId(){
	  var userId = document.getElementById("userId").value;
	  $.getJSON("checkUserId", { userLoginId: userId }, function(result) {
		  $.each(result, function (index, value) {
			 if(index=="value"){
				if(value=="userId Already exists"){
					document.getElementById("error").innerHTML="UserId already Taken";
					document.getElementById("error").style.display = "inline-block";
				}else{
					document.getElementById("error").innerHTML="";
					document.getElementById("error").style.display = "none";
				}
			 }
			  
		  });
	  }); 
	  
	  
  }
  function genderselect(ch){
	  alert(ch.value);
	  if(ch.value!="other"){
	  	document.getElementById("othergend").style.display="none";
	  }else{
		  document.getElementById("othergend").style.display="block";
	  }
  }
</script>
</head>
<body>
					<div id="sub-neew">
					<h1>Jury Charge Selection System</h1>
			<a href="home.jsp">Home</a><c:if test="${not empty sessionScope.userLogin}" >
			
	<c:if test="${empty sessionScope.isAdmin}" >		
 | <a href="newcase">Create a New Case</a> | <a href="viewpreviouscase">View Previous Cases</a> |
 </c:if>
 </c:if>
 <div id="fneew"> <a href="about.jsp">About Us</a> | <a href="contact.jsp">Contact Us</a> |
 
  <c:if test="${not empty sessionScope.userLogin}" ><c:if test="${empty sessionScope.isAdmin}" ><a href="changeuserpassword">Change password</a></c:if> </c:if>
 
 
 <c:if test="${not empty sessionScope.userLogin}" >
 <form name="searchForm" id="searchForm" action="searchResults" style="float:left">
<input type="text" name="search" id="search" /> <button onclick="return searchValidate()">go</button>
</form>
</c:if>

  </div>
 <c:if test="${not empty sessionScope.isAdmin}" >
      <a href="changecases">Edit Cases</a> 
  
  </c:if>
  <c:if test="${not empty sessionScope.userLogin}" >
  <a href="logout">Logout</a> 
	</c:if>	
	  <c:if test="${empty sessionScope.userLogin}" >
  <a href="login.jsp">Login</a> 
	
			
	<a href="register.jsp">Register</a> |
		</c:if>		
			</div>

  <div class="form">
  <h2 class="message">User Sign Up</h2>
    <form class="login-form" method="post" action="userregistration" name="myForm" >
    <p style="color:red;font-weight: bold"> ${param.message}</p>
   Select UserType: <select name="usertype">
  <option value="Attorney">Attorney</option>
  <option value="Judge">Judge</option>
 
</select><br/><br/>
      <input type="text" placeholder="First Name*" name="fname"  />
      <input type="text" placeholder="Last Name*" name="lname" />
      <input type="text" placeholder="Middle Name (Optional)" name="mname" />
      
      <input type="text" id="userId" onblur="checkUserId()" placeholder="User Id*" name="username" /><span style="display:none" id="error"></span>
      <input type="password" placeholder="Password*"  name="password" />
      <input type="password" placeholder="Retype Password*"  name="repwd" /><br/>
       Gender: <input type="radio" name="gender" onclick="genderselect(this)" value="male" checked>Male
               <input type="radio" name="gender"onclick="genderselect(this)"  value="female">Female
                <input type="radio" name="gender" onclick="genderselect(this)" id="other" value="other">Other
                 <input type="text" id="othergend" style="display:none"><br>
                 
      <input type="text" placeholder="Email Id*" name="email" />
      <input type="text" placeholder="Phone Number*" name="phone" />
      <input type="text" placeholder="Address*" name="address" />
      <input type="text" placeholder="City*" name="city" />
      <br><br>
      <input type="text" name="zipcode" placeholder="Zip code*"  />
      <br><br>
      <select  name="securityQuestion1" type="select" style="width:350px;">
      <option value="">Select Question one</option>
      <option value="what is your first pet name?">what is your first pet name?</option>
      <option value="what is your favourite movie?">what is your favourite movie?</option>
      </select>
      <input type="text" name="sa1" placeholder="Answer" style="width:190px;" /><br><br>
      <select name="securityQuestion2" type="select" style="width:350px;">
            <option value="">Select Question one</option>
      <option>What is the first name of your best friend in high school?</option>
      <option>What primary school did you attend?</option>
      </select>
        <input type="text" name="sa2" placeholder="Answer" style="width:190px;" />
      <input type="submit" name="register"  onclick="return validateForm(this)" value="Register">
    </form>
    Already Registered <a href="login.jsp">Click Here</a> to login
  </div>

</body>
</html>