
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>book</title>
    <style>
        body {
            font-style: italic;
            text-align: center;
        }
    </style>
</head>
<body>
    <h1>Book, id = ${book.id}</h1>
    <div>Title = ${book.title}</div>
    <div>Author = ${book.author}</div>
    <div>ISBN = ${book.isbn}</div>
    <div>Price = ${book.price}</div>
    <div>Cover = ${book.cover}</div>
</body>
</html>
