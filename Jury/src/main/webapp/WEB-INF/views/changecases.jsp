<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true" %>
<html>
<head>
<title>Jury Charge Selection System</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>

function namevalidate(){
	var x = document.getElementById("casetype").value;
	var y = document.getElementById("subcasetype").value;
	alert(y);
   if(x=="...Select Case Type..."){
	   alert("please select case type")
	   return false;
   }
   else if(y=="...Select Case Type..." || y==1){
	   alert("please select sub case type")
	   return false;
   }
}
function searchAjax() {
	var data = {}
	data["query"] = $("#query").val();

 	/* $.ajax({
		type : "GET",
		contentType : "application/json",
		url : "/Jury/getcasetypes?casetype="+document.getElementById("casetype").value,
		data : JSON.stringify(data),
		dataType : 'json',
		timeout : 100000,
		success : function(data) {
			console.log("SUCCESS: ", data);
			display(data);
		},
		error : function(e) {
			console.log("ERROR: ", e);
			display(e);
		},
		done : function(e) {
			console.log("DONE");
		}
	});  */
	// get the form values
	var casetype = $('#casetype').val();
	$('#subcasetype').html('');
	$('#subcasetype').append($('<option>', {
	    value: 1,
	    text: '...Select Sub Case Name...'
	}));
	$.getJSON("getsubcasetypes", { casetype: casetype }, function(result) {

	    console.log("SUCESS:"+result);
	    $.each(result, function (index, value) {
	    	  console.log(value.casedescription);
	    	  $('#subcasetype').append($('<option>', { 
	    	        value: value.subcase,
	    	        text : value.subcase 
	    	    }));
	    	  // Will stop running after "three"
	    	  //return (value !== 'three');
	    	});
	    
	 }); 
}

function validateForm(){
	var x = document.getElementById("maincasetype").value;
	var y = document.getElementById("subcasename").value;
	alert(y);
   if(x=="...Select Case Type..."){
	   alert("please select case type")
	   return false;
   }
   else if(y==""){
	   alert("please enter sub case ")
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
<div id="content-left">
<div class="miniform">
 <h3>Add a sub Case</h3>
 <form id="addSubcase" name="addSubcase" action="addSubcase">
 <p style="color:red;font-weight: bold">${param.message}</p>
   <select name="maincase" id="maincasetype" type="select">
     	<option>...Select Case Type...</option>
 		<c:forEach items="${caseTypes}" var="caseNames">
        	<option>${caseNames.casename}</option>
		</c:forEach>
     </select><br/><br/><br/>
      <input type="text" placeholder="Sub case" id="subcasename" name="subcasename"  />
      <input type="submit" name="Add"  onclick="return validateForm(this)" value="Add">
      </form>
 </div>
<div class="form">
<h3>Change Case</h3>
    <form class="login-form" method="post" id="newcase" name="myForm" action="editcaseinfo">
    <select name="casetype" id="casetype" type="select" onchange="searchAjax()">
     	<option>...Select Case Type...</option>
 		<c:forEach items="${caseTypes}" var="caseNames">
        	<option>${caseNames.casename}</option>
		</c:forEach>
     </select><br/><br/><br/>
        <select name="subcasetype" id="subcasetype" type="select">
     	<option>...Select Sub Case Name...</option>
     </select><br/><br/><br/>
     
     
      <input type="submit" onclick="return namevalidate(this)" name="submit" value="Proceed" style="width:125px;float:right;">
      <input type="button" name="" value="Back" onclick="history.go(-1);" style="width:125px;">
    </form>
 </div>
 </div>
<div id="content-right">
<img src="images/viewcase.png" width="300">
</div>
</div>
</body>
</html>