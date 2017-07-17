<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true" %>
<html>
<head>
  <title>Jury Charge Selection System</title>
  <link rel="stylesheet" type="text/css" href="css/style.css">
  <style>
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}
th, td {
    padding: 5px;
    text-align: left;
}
</style>
  
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
        <h3>Contact Us</h3>
        <p><b>Team Members:</b>
<table >
<tr>
<th>Name</th>
<th>Email</th>
<th>Phone</th>
</tr>
<tr>
<td>Abhinav Nallagonda</td>
<td>nallagondaa@mail.sacredheart.edu</td>
<td>203-612-0506</td>
</tr>
<tr>
<td>Sravanth Kumar Reddy Guddannagari</td>
<td>guddannagaris@mail.sacredheart.edu</td>
<td>475-449-7767</td>
</tr>
<tr>
<td>Vamshi  Krishna Velide</td>
<td>velidev@mail.sacredheart.edu</td>
<td>203-690-7698</td>
</tr>
<tr>
<td>Mehar Mani Linga</td>
<td>lingam@mail.sacredheart.edu</td>
<td>571-577-0403</td>
</tr>
</table>
<p>Sacred Heart University</p>
<p>5151 Park Avenue</p>
<p>Fairfield, CT 06825-100</p>
<p>Fairfield, CT 06825-100</p>




</p>
        
        </div>
      </div>
    </div>
  </body>
  </html>