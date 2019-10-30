<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <title>Student View</title>
    <script>
        function f_modify() {
            location.href = "/student/modify/1";
        }
        function f_delete() {
            location.href = "/student/delete/1";
        }
        function f_list() {
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
    <h1>Student View</h1>
    <hr />

    <table>
        <tbody>
            <tr>
                <th>Seq : </th>
                <td>${student.seq}</td>
            </tr>
            <tr>
                <th>Name : </th>
                <td>${student.name}</td>
            </tr>
            <tr>
                <th>Grade : </th>
                <td>${student.grade}</td>
            </tr>
            <tr>
                <th>Age : </th>
                <td>${student.age}</td>
            </tr>
            <tr>
                <th>Hobby : </th>
                <td>${student.hobby}</td>
            </tr>
        </tbody>
    </table>

    <hr />

    <button onclick="f_modify()">수정</button>
    <button onclick="f_delete()">삭제</button>
    <button onclick="f_list()">목록</button>
</body>
</html>