<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Books</title>
    <link href="css/style.css" rel="stylesheet">
    <style>
        *{
            font-style: serif;
            text-align: center
            }
    </style>
</head>
<body>
    <h1>Books</h1>
    <table id="t1">
        <tr>
            <th>No</th>
            <th>ID</th>
            <th>TITLE</th>
            <th>AUTHOR</th>
            <th>PAGES</th>
            <th>ISBN</th>
            <th>PRICE</th>
            <th>COVER</th>
        </tr>
        <c:forEach items="${books}" var="book" varStatus="counter">
            <tr>
                <td>${counter.count}</td>
                <td>${book.id}</td>
                <td>${book.title}</td>
                <td>${book.author}</td>
                <td>${book.pages}</td>
                <td>${book.isbn}</td>
                <td>${book.price}</td>
                <td>${book.cover}</td>
            </tr>
        </c:forEach>

    </title>
</body>
</html>