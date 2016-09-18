<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" %>
<html>
<head>
	<title>TestTask's BookLib Add your Book</title>
</head>
<body>
<h1>
	Hello again, User! 
</h1>

<p>  Simply fill this form and press "Submit" button. All fields must be filled.</p>

<p> ${message} <br/>

<form method="POST" action="addBook" enctype="multipart/form-data">

	<div style="margin:5px" width="100px"> Book's name: </div> <input size="100" type="text" name="nameBook" value="${book.name}" /> <br/> 
	<div style="margin:5px" width="100px"> Author's name: </div> <input size="100"  type="text" name="nameAuthor" value="${book.author}" /> <br/> 
	<div style="margin:5px" width="100px"> Book's description:</div> <textarea name="desc" cols="95" rows="20">${book.description}</textarea> <br/> 
	<div style="margin:5px" width="100px"> Book's cover image:</div> <input type="file" name="img"/>
	<p style="margin:5px" >

	<input type="submit" name="submit" />

</form>

</body>
</html>