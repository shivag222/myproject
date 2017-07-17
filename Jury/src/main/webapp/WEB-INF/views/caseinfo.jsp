<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true" %> <!DOCTYPE HTML>
<html>
<head>
<title>Jury Charge Selection System</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="page">
<div id="neew">
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
<div id="content-center">
<c:if test="${not empty sessionScope.userLogin}" ><span style="float:right;color:red;font-weight:bold;">Hi ${sessionScope.userLogin}! ${message}</span></c:if>
<a href="deleteCase?id=${usercase.id}">Delete this case</a>
<h4>Jury instructions related to the Selected Case: ID:${usercase.id} </h4>
<h3> ${usercase.accusedname}&nbsp;${usercase.subcase} Case</h3>
  ${usercase.casetype}
  <br />
 </div>
<div id="content-right">
 <input type="button" name="" value="Back" onclick="history.go(-1);" style="width:100px;float:left;">
</div>
</body>
</html>