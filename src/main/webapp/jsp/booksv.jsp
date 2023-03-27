
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>book</title>
    <style>
        body {
            font-style: italic;
            text-align: center;
        }
        caption {
            text-align: center;
            font-size: 35px;
        }
        th {
            text-align: left;
        }
    </style>
</head>
<body>
    <table id="t1" width="100%">
        <caption>Book</caption>
        <tr>
            <th>No</th>
            <th>ID</th>
            <th>TITLE</th>
            <th>AUTHOR</th>
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
                <td>${book.isbn}</td>
                <td>${book.price}</td>
                <td>${book.cover}</td>
            </tr>
        </c:forEach>

    </table>
</body>
</html>
