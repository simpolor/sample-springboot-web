<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <title>Student Modify</title>
    <script>
        function fnSubmit() {
            var form = document.getElementById("studentForm");
            form.submit();
        }
        function fnList() {
            location.href = "/student/list";
        }
    </script>
    <style>
        table {
            text-align: center;
        }
    </style>
</head>
<body>
    <h1>Student Modify</h1>
    <hr />

    <form id="studentForm" action="/student/modify/${student.seq}" method="post">
        <table>
            <tbody>
            <tr>
                <th>Name : </th>
                <td><input type="text" name="name" id="name" value="${student.getName()}" /></td>
            </tr>
            <tr>
                <th>Grade : </th>
                <td><input type="text" name="grade" id="grade" value="${student.getGrade()}" /></td>
            </tr>
            <tr>
                <th>Age : </th>
                <td><input type="text" name="age" id="age" value="${student.getAge()}" /></td>
            </tr>
            <tr>
                <th>Hobbies : </th>
                <td><input type="text" name="hobbies" id="hobbies" value="${student.getHobbies()}" /></td>
            </tr>
            </tbody>
        </table>
    </form>

    <hr />

    <button onclick="fnSubmit()">수정</button>
    <button onclick="fnList()">목록</button>

</body>
</html>