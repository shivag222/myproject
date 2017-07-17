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
			<div id="neew">
			<c:if test="${not empty sessionScope.userLogin}" ><span style="float:right;color:red;font-weight:bold;">Hi ${sessionScope.userLogin}!</span></c:if>
<div id="content">
<div class="form">

<h2>View Previous Case</h2>
<!--  <form class="login-form" method="post" action="case-info.html">
      Enter Accused Name : <input type="text" placeholder="Accused Name" name="user"/><br/><br/><br/>
      Enter Case ID : <input type="text" placeholder="Case Name"  name="pw"/><br/><br/><br/>
      <input type="submit" name="submit" value="View Case Details">
    </form> -->
    <table width="760" border="1" >
        	<tr>
        	<th>Case_Id</th>
        	<th>Case Name</th>
     
        	<th>Date and Time</th>
        	</tr>
		<c:forEach items="${usercase}" var="caseNames">
        	
        	<tr>
        	<td>${caseNames.id}</td>
        	<td><a href="viewcase?id=${caseNames.id}">${caseNames.subcase}&nbsp;Case</a></td>
      
        	<td>${caseNames.createTime}</td>
        	</tr>
        	</c:forEach>
        	</table><br/>
        	 <input type="button" name="" value="Back" onclick="history.go(-1);" style="width:100px;float:center;">
        	
    
</div>	
</div>
</div>
</body>
</html>