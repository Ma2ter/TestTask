<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" %>
<html>
<head>
	<title>TestTask's BookLib Search Engine</title>
</head>
<body>
<h1>
	Hello again, User!  
</h1>

<p> Let's search for a specific book, shall we?</p>

<form method="POST" action="./searchResult">
	<input type="radio" name="searchMethod" checked="checked" value="name"/> Search by Book's name <br/>
	<input type="radio" name="searchMethod" value="author"/> Search by Author's name <br/>

	<input type="text" name="value"/> <br/>

	<input type="submit" name="submit" />
</form>

</body>
</html>
