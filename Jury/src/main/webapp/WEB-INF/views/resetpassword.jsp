<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true" %>
<html>
<head>
<title>Jury Charge Selection System</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<script type="text/javascript">
 function validateForm() {
  var x = document.forms["myForm"]["sa1"].value;
    if (x == null || x == "") {
        alert("please answer security question 1");
        return false;
    }
    var x = document.forms["myForm"]["sa2"].value; 
    if (x == null || x == "") {
        alert("please answer security question 2");
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
  <h2 class="message">Please answer the Below Security Questions</h2>
    <form class="login-form" method="post" action="resetpassword" name="myForm">
     ${user.secquestion1} <input type="text" placeholder="" name="sa1"  /><br />
      <input type="hidden" placeholder="" name="username" value="${user.username}"  />
      <c:if test="${not empty sessionScope.count}" >
      	 <input type="hidden" placeholder="" name="count" value="${count}"  />
      </c:if>
            <c:if test="${empty sessionScope.count}" >
      	 <input type="hidden" placeholder="" name="count" value="0"  />
      </c:if>
      <input type="submit" name="submit" onclick="return validateForm(this)" value="Request Password">
      <p style="color:red;font-weight: bold">${error}</p>
      

    </form>
   </div>

</body>
</html>