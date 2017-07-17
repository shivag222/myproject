<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true" %>
<html>
<head>
<title>Jury Charge Selection System</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<script>
function validateQuestions(){
	var count = document.getElementById("count").value;
	var disable = false;
	
	if(document.getElementById("accusedname").value==""){
		alert("please enter accused name");
		return false;
	}
	for(var i =1;i<=count-1;i++){
		var radios = document.getElementsByName("q"+i);
		for(var j = 0; j < radios.length; j++ ) {
		        if( radios[j].checked ) {
		            if(radios[j].value!= "no"){
		            	alert("q"+i+":yes");
		            	disable = true;
		            }
		        }
		    }	
	}

	if(disable){
		document.getElementById("casequestions").submit();
		
		//window.open('AssaultContact.pdf', '_blank', 'fullscreen=yes')
	}
	else{
		
		alert("please answer atleast one question");
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
<div id="content-left">


<h3>Jury Charge Selection System</h3>
    <form class="login-form" method="post" id="casequestions" action="caseinfo">
		<input type="hidden" name="casename" id="casename"  value="${casename}">
		<br><br>
		
<!-- 	<p>	
    1)what defendant said to the plaintiff was a statement of opinion rather than fact ??<br/>
    <input type="radio" name="1" id="r1y" value="yes">Yes&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name="1" value="no" checked>NO<br/><br/>
    2)whether the representation was true???<br/>
     <input type="radio" name="2" id="r2y" value="yes">Yes&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name="2" value="no" checked>NO<br/><br/>
    3) whether defendant knew or believed it was false and made the representation with intent to deceive the plaintiff??<br/>
     <input type="radio" name="3" id="r3y" value="yes">Yes&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name="3" value="no" checked>NO<br/><br/>
    4) ultimately conclude that there was no justifiable reliance by the plaintiff or even if there was not a substantial factor in plaintiff's decision to enter into the transaction??<br/>
     <input type="radio" name="4" id="r4y" value="yes">Yes&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name="4"value="no" checked>NO<br/><br/>
     </p> -->
     <c:set var="count" value="1"/>
     <c:forEach items="${scd}" var="sc" >
     	<c:set var="subcase" scope="session" value="${sc.subcase}"></c:set>
     	<p>${count})${sc.casequestions}</p>
     	<p>A)${sc.verdict}</p><a href="editquestioninfo?id=${sc.id}">Edit</a>&nbsp;&nbsp;&nbsp;<a href="deletecaseqa?id=${sc.id}&subcasetype=${sc.subcase}">Delete</a>
     	<c:set var="count" value="${count+1}"/>
     </c:forEach>
     <input type="hidden" name="count" id="count" value="${count}"><br><br>
     <a href="addcaseqa?subcasetype=${casename}">ADD case QA</a>
    </form>
 </div>
<div id="content-right">

</div>
</div>
</body>
</html>