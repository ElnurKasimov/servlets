<%@ page import="com.softserve.itacademy.model.Priority" %>
<%@ page import="com.softserve.itacademy.model.Task" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new Task</title>

    <style>
        <%@include file="../styles/main.css"%>
    </style>
    
</head>
<body>
    <%@include file="header.html"%>

    <form action="/create-task" method="post">
        <label for="title">Title: </label>
        <input type="text" id="title" name="title">
        <label for="priority">Priority: </label>
        <input type="text" id="priority" name="priority">
        <input type="submit" value="Create">
        <input type="reset" value="Clear">
    </form>

</body>
</html>
