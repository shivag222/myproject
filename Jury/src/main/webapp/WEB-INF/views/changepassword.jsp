<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true" %>
<html>
<head>
<title>Jury Charge Selection System</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<script type="text/javascript">
 function validateForm() {
  var x = document.forms["myForm"]["newpassword"].value;
    if (x == null || x == "") {
        alert("please enter new password");
        return false;
    }
    var y = document.forms["myForm"]["repeatnewpassword"].value; 
    if (y == null || y == "") {
        alert("please repeat the new password");
        return false;
    }
    if(x!=y){
    	alert("New password should match with confirm password");
    	return false;
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
  <h2 class="message">Change Password</h2>
    <form class="login-form" method="post" action="changepassword" name="myForm">
    <p style="color:red;font-weight: bold"> ${message}</p>
     New Password: <input type="text" placeholder="" name="newpassword"  /><br />
     Confirm Password: <input type="text" placeholder="" name="repeatnewpassword"  /><br />
     <input type="hidden" placeholder="" name="username" value="${user.userLoginId}"  />
      <input type="hidden" placeholder="" name="password" value="${user.password}"  />
      <input class= "radio" type="submit" name="submit" onclick="return validateForm(this)" value="Change Password">
    </form>
   </div>

</body>
</html>