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

    <h2>Create new Task</h2>

    <% if (request.getAttribute("errorMessage") != null) { %>
    <p><%= request.getAttribute("errorMessage") %></p>
    <% } %>

    <form action="/create-task" method="post" id="create-task">
        <table>
            <tr>
                <td>
                    <label for="title">Name: </label>
                </td>
                <td>
                    <input type="text" id="title" name="title" required>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="priority">Priority: </label>
                </td>
                <td>
                    <select name="priority" id="priority" form="create-task" required>
                        <option value="LOW">Low</option>
                        <option value="MEDIUM">Medium</option>
                        <option value="HIGH">High</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="Create">
                </td>
                <td>
                    <input type="reset" value="Clear">
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
