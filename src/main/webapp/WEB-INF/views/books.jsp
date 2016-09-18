<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>TestTask's BookLib</title>
</head>
<body>
<h1>
	Hello, User! See what we got here for ya!
</h1>

<h3>Books</h3>

    <c:forEach items="${booksList}" var="book">
    		<h3> <a href="./books/${book.id}"> ${book.name} </a> by 
    		<a href="./searchResult?searchMethod=author&value=${book.author}"> ${book.author} </a></h3>
    </c:forEach>
    
    <a href="./" style="margin-right: 40px"> Back to Main</a> 
    <a href="./add" style="margin-right: 40px"> add New Book</a> 
    <a href="./search">Search</a>
    
</body>
</html>
