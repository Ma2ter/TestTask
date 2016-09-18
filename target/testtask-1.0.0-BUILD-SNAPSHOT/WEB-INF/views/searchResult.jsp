<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Result's</title>
</head>
<body>

<form method="POST" action="./searchResult">
	<input type="radio" name="searchmethod" checked="checked" value="book"/> Search by Book's name <br/>
	<input type="radio" name="searchMethod" value="author"/> Search by Author's name <br/>

	<input type="text" name="value"/> <br/>

	<input type="submit" name="submit" />
</form>

<br/>

<h1> Results found containing <b> ${searchValue} : ${resultNum} </b> </h1>

<h3>Books</h3>

    <c:forEach items="${booksList}" var="book">
    		<h3> <a href="./books/${book.id}"> ${book.name} </a> by 
    		<a href=""> ${book.author} </a></h3>
    </c:forEach>
    


</body>
</html>