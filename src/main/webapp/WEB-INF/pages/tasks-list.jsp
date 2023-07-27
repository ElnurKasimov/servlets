<%@ page import="com.softserve.itacademy.model.Task" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of Tasks</title>

    <style>
        <%@include file="../styles/main.css"%>
    </style>

</head>
<body>
    <%@include file="header.html"%>
    <br>
    <h2><b>List of Tasks</h2>
    <br>
    <table border="1">
        <tr>
            <th>&numero;</th>
            <th>Name</th>
            <th>Priority</th>
            <th colspan="3">Operations</th>
        </tr>
        <%
            List<Task> tasks = (List<Task>) request.getAttribute("tasks");
            for(int i=0; i < tasks.size(); i++) {
        %>
        <tr>
            <td><%=i+1%></td>
            <td><%=tasks.get(i).getTitle()%></td>
            <td><%=tasks.get(i).getPriority()%></td>
            <td>
                <a href="/read-task?id=<%=tasks.get(i).getId() %>">Read</a>
            </td>
            <td>
                <a href="/edit-task?id=<%=tasks.get(i).getId()%>">Edit</a>
            </td>
            <td>
                <a href="/delete?id=<%=tasks.get(i).getId()%>">Delete</a>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>
