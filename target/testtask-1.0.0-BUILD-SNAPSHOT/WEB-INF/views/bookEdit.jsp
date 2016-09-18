<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1>
	Hello again, User! 
</h1>

<p>  Simply change data in this form and press "Submit" button. All fields must be filled, except cover, in case you don't want to change it.</p>

<p> ${message} <br/>

<form method="POST" action="editBook" enctype="multipart/form-data">

	<div style="margin:5px" width="100px"> Book's name: </div> <input size="100" type="text" name="nameBook" value="${book.name}" /> <br/> 
	<div style="margin:5px" width="100px"> Author's name: </div> <input size="100"  type="text" name="nameAuthor" value="${book.author}" /> <br/> 
	<div style="margin:5px" width="100px"> Book's description:</div> <textarea name="desc" cols="95" rows="20">${book.description}</textarea> <br/> 
	<div style="margin:5px" width="100px"> Book's cover image:</div> <input type="file" name="img"/>
	<p style="margin:5px" >

	<input type="submit" name="submit" />
</form>

</body>

</body>
</html>