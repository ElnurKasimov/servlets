<%@ page import="com.softserve.itacademy.model.Task" %>
<%@ page import="com.softserve.itacademy.model.Priority" %>
<%@ page import="com.softserve.itacademy.repository.TaskRepository" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Read existing Task</title>

    <style>
        <%@include file="../styles/main.css"%>
    </style>

</head>
<body>
<%
    Task task = (Task) request.getAttribute("task");
%>
<%@include file="header.html"%>
<h2>Read exiting task</h2>
<table>

    <tr>
        <td><p>ID: <p></td>
        <td><b><%=task.getId()%></b></td>
    </tr>
    <tr>
        <td>Name: </td>
        <td><b><%=task.getTitle()%></b></td>
    </tr>
    <tr>
        <td>Priority: </td>
        <td><b><%=task.getPriority()%></b></td>
    </tr>
    </table>
</body>
</html>
