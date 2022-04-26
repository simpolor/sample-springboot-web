<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <title>Student Form</title>
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
    <h1>Student Form</h1>
    <hr />

    <form id="studentForm" role="form" action="/student/register" method="post">
        <table>
            <tbody>
                <tr>
                    <th>Name : </th>
                    <td><input type="text" name="name" id="name" /></td>
                </tr>
                <tr>
                    <th>Grade : </th>
                    <td><input type="text" name="grade" id="grade" /></td>
                </tr>
                <tr>
                    <th>Age : </th>
                    <td><input type="text" name="age" id="age" /></td>
                </tr>
                <tr>
                    <th>Hobbies : </th>
                    <td><input type="text" name="hobbies" id="hobbies" /></td>
                </tr>
            </tbody>
        </table>
    </form>

    <hr />

    <button onclick="fnSubmit()">등록</button>
    <button onclick="fnList()">목록</button>

</body>
</html>