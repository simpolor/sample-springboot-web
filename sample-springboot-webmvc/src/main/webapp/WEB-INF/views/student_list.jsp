<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <title>Student List</title>
    <script>
        function f_register() {
            location.href = "/student/register";
        }
    </script>
    <style>
        table {
            text-align: center;
        }
    </style>
</head>
<body>
    <h1>Student List</h1>
    <hr />

    <table>
        <thead>
            <tr>
                <th>seq</th>
                <th>name</th>
                <th>grade</th>
                <th>age</th>
                <th>hobby</th>
            </tr>
        </thead>

        <tbody>
            <c:forEach var="student" items="${students}">
            <tr>
                <td>${student.seq}</td>
                <td><a href="/student/view/${student.seq}">${student.name}</a></td>
                <td>${student.grade}</td>
                <td>${student.age}</td>
                <td>${student.hobby}</td>
            </tr>
            </c:forEach>
        </tbody>
    </table>

    <hr />

    <button onclick="f_register()">등록</button>

</body>
</html>