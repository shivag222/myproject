<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true" %>
<html>
<head>
  <title>Jury Charge Selection System</title>
  <link rel="stylesheet" type="text/css" href="css/style.css">
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
			<div id="neew">
			
      <div id="content">
       
        <div style="text-align:left;padding:50px;">
        <h3>About Us</h3>
        <p>The Jury Charge selection system will determine the suitable instructions. The attorneys will find the corresponding instructions using the system by entering answers or keywords for the trial. This software reduces work of gathering information from various sources. With a keyword as input all the related information will be shown at one place without missing anything. This saves a lot of time for not only attorneys but also for the court.</p>
        </div>
      </div>
      </div>
    
  </body>
  </html>