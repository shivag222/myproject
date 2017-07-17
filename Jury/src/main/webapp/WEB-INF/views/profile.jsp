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
  
  window.onload=function(){
	  alert();
	  
	  document.getElementById("sq1").value="${user.secquestion1}";
	  document.getElementById("sq2").value="${user.secquestion2}";
	  document.getElementById("usertype").value="${user.usertype}";
  }
</script>
</head>
<body>
					<div id="sub-neew">
					<h1>Jury Charge Selection System</h1>
			<a href="home">Home</a><c:if test="${not empty sessionScope.userLogin}" >
			
	<c:if test="${empty sessionScope.isAdmin}" >		
 | <a href="newcase">Create a New Case</a> | <a href="viewpreviouscase">View Previous Cases</a> |
 </c:if>
 </c:if>
 <div id="fneew"> <a href="about.jsp">About Us</a> | <a href="contact.jsp">Contact Us</a> |
 
 
 
 
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
  <a href="profile">Edit Profile</a> 
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
  <c:if test="${not empty sessionScope.userLogin}" ><span style="float:right;font-size:20px;font-weight:bold;">Hi ${sessionScope.userLogin}!</span></c:if>
  <br/><h2 class="message">My Profile</h2>
    <form class="login-form" method="post" action="editProfile" name="myForm" >
    
    <p style="color:red;font-weight: bold"> ${param.message}</p>
     Select UserType: <select name="usertype" id="usertype">
  <option value="Attorney">Attorney</option>
  <option value="Judge">Judge</option>
 
</select><br/><br/>
      <input type="text" placeholder="First Name*" name="fname"  value="${user.fname}" />
      <input type="text" placeholder="Last Name*" name="lname" value="${user.lname}"/>
      <input type="text" placeholder="Middle Name (Optional)" name="mname"   value="${user.mname}"/>
      <input type="text" id="userId" onblur="checkUserId()" placeholder="User Id*" name="username"  value="${sessionScope.userLogin}" /><span style="display:none" id="error"></span>
       <input type="text" placeholder="Gender" name="gender"   value="${user.gender}"/>
      <input type="text" placeholder="Email Id*" name="email" value="${user.email}" />
      <input type="text" placeholder="Phone Number*" name="phone" value="${user.phone}"/>
      <input type="text" placeholder="Address*" name="address" value="${user.address}"/>
      <input type="text" placeholder="City*" name="city" value="${user.city}" />
      <input type="hidden" placeholder="City*" name="password" value="${user.password}" />
       <input type="text" name="zipcode" placeholder="Zip code*" value="${user.zipcode}" />
      <br><br>
      <select  name="securityQuestion1" id ="sq1" type="select" value="${user.secquestion1}"  style="width:350px;">
      <option value="">Select Question one</option>
      <option value="what is your first pet name?">what is your first pet name?</option>
      <option value="what is your favourite movie?">what is your favourite movie?</option>
      </select>
      <input type="text" name="sa1" placeholder="Answer" value="${user.secanswer1}"style="width:190px;" /><br><br>
      <select name="securityQuestion2" type="select" id="sq2" style="width:350px;">
            <option value="">Select Question one</option>
      <option>What is the first name of your best friend in high school?</option>
      <option>What primary school did you attend?</option>
      </select>
        <input type="text" name="sa2" placeholder="Answer" value="${user.secanswer2}"style="width:190px;" /><br/>
        <a href="editpassword.jsp">Change password</a>
      <input type="submit" name="register"  onclick="return validateForm(this)" value="Update profile">
    </form>
  </div>

</body>
</html>