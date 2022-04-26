<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <title>Student View</title>
    <script>
        function fnModify() {
            location.href = "/student/modify/1";
        }
        function fnDelete() {
            location.href = "/student/delete/1";
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
    <h1>Student Detail</h1>
    <hr />

    <table>
        <tbody>
            <tr>
                <th>Id : </th>
                <td>${student.id}</td>
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
                <th>Hobbies : </th>
                <td>${student.hobbies}</td>
            </tr>
        </tbody>
    </table>

    <hr />

    <button onclick="fnModify()">수정</button>
    <button onclick="fnDelete()">삭제</button>
    <button onclick="fnList()">목록</button>
</body>
</html>