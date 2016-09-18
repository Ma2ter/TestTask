<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1 align="center" style="margin-top: 50px"> <b> ${book.name} </b> by 
<a href="../searchResult?searchMethod=author&value=${book.author}"> ${book.author} </a> </h1>
	
	<div style="padding: 50px 20%;">
	<img align="left" width="200" height="400" src="/testtask/resources/img/${book.imageURI}" alt="${book.name}" style="float:left; margin:20px " />
	
	<p align = "center"> ${book.description}

<br/>

	<a href="../books" style="margin-right: 50px" > View all books</a> 
	<a href="../search" style="margin-right: 50px"> Search for another book</a>
	<a href="${book.id}/edit" style="margin-right: 50px" > Edit current book </a>
	<a href="${book.id}/delete"> Delete current book </a>
</div>
</body>
</html>