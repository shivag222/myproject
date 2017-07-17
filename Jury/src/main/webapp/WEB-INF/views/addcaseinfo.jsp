<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true" %>
<html>
<head>
<title>Jury Charge Selection System</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<script>
function validateQuestions(){
	var x = document.getElementById("q").value;
	var y = document.getElementById("q").value;
	if(x==""){
		alert("Question cannot be empty");
		return false;
	}
	if(y==""){
		alert("Answer cannot be empty");
		return false;
	}
	
	
}

/* function checkQuestion(){
	var count = document.getElementById("count").value;
	var disable = false;
	var temp = 0;
	alert("hi"+count);
	for(var i =1;i<=count-1;i++){
		var radios = document.getElementsByName("q"+i);
		for(var j = 0; j < radios.length; j++ ) {
			
		        if( radios[j].checked ) {
		            if(radios[j].value!= "no" && !radios[j].disabled){
		            	alert("q"+i+":yes"+radios[j].disabled);
		            	disable = true;
		            	temp = i;
		            }
		        }
			
		}	
	}
	if(disable){
		for(var i =temp+1;i<=count-1;i++){
			var radios = document.getElementsByName("q"+i);
			for(var j = 0; j < radios.length; j++ ) {
				//radios[j][1].checked = false;
				radios[j].disabled  = true;  
			}
		}
		for(var k = temp-1; k <=0; k-- ) {
			//radios[j][1].checked = false;
			var radios = document.getElementsByName("q"+k);
			radios[k][1].checked  = true;  
		}
	}else{
		alert(disable)
		for(var i =temp+1;i<=count-1;i++){
			var radios = document.getElementsByName("q"+i);
			for(var j = 0; j < radios.length; j++ ) {
				 radios[j].disabled  = false;  
			}
		}
		
	} 
	
}*/
</script>
</head>
<body>
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
  <a href="logout">Logout</a> 
	</c:if>	
	  <c:if test="${empty sessionScope.userLogin}" >
  <a href="login.jsp">Login</a> 
	
			
	<a href="register.jsp">Register</a> |
		</c:if>		
			</div>
<div id="content-left">


<h3>Edit Case Info</h3>
    <form class="login-form" method="post" id="casequestions" action="addnewcaseqa">
 	Q.<textarea name="q" id="q" rows="4" cols="50"></textarea>
	A.<textarea name="a" rows="4" id="a" cols="50"></textarea><br><br>
	<input type="hidden" name="subcasetype" value="${subcasetype}" />
     <input type="submit"  onclick="return validateQuestions(this)" name="submit" value="Proceed">
    </form>
 </div>
<div id="content-right">
<img src="images/view1.png" width="300">
</div>
</div>
</body>
</html>