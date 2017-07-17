<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true" %>
<html>
<head>
<title>Jury Charge Selection System</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<script type="text/javascript">
  function validateForm() {
 /*    var x = document.forms["myForm"]["user"].value;
    if (x == null || x == "") {
        alert("Username Should not be empty");
        return false;
    }
    var y = document.forms["myForm"]["pw"].value;
    if (y == null || y == "") {
        alert("Password Should not be empty");
        return false;
    } */
/*     if(x != "admin" && y != "admin"){
        alert("Invalid Credintials");
        return false;
    } */
    if (document.getElementById("username").value == "") {
    	 alert("Username Should not be empty");
         return false;
    }
    if (document.getElementById("password").value == "") {
    	alert("Password Should not be empty");
        return false;
    }
    document.getElementById("myForms").submit();
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
  <h2 class="message">User Login</h2>
    <form class="login-form" method="post" action="logincheck" name="myForm" id="myForms">
    <p style="color:red;font-weight: bold"> ${param.message}</p>
    Login as: <select name="usertype">
  <option value="Attorney">Attorney</option>
  <option value="Judge">Judge</option></select><br/><br/>
 
      <input type="text" placeholder="username" id="username" name="username"  /><br />
      <input type="password" placeholder="password" id="password"  name="password"  /><br />
      <input type="submit" onclick="return validateForm(this)" name="login" value="Login"><br />
    </form>
   
    <a href="recoverpassword.jsp">Forgot Password</a>
  </div>

</body>
</html>