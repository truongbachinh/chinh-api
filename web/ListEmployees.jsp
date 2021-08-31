<%@ page import="com.google.gson.Gson" %>
<%@ page import="model.Employee" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Employee Store Application</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<center>
    <h1>Employee Management</h1>
    <h2>
        <a href="api-employee?action=new">Add New Employee</a>
        &nbsp;&nbsp;&nbsp;
        <a href="api-employee?action=list">List All Employee</a>
        <button class="btn btn-info" id="getData1">Get Element1</button>
        <button class="btn btn-info" id="getData2">Get Element2</button>
    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Employee</h2></caption>
        <tr>
            <th>ID</th>
            <th>First name</th>
            <th>Last name</th>
            <th>email</th>
            <th>hireDate</th>
            <th>jobId</th>
            <th>salary</th>
            <th>Actions</th>
        </tr>
    </table>
    <ul id="somediv"></ul>
</div>
</body>
</html>
<script type="text/javascript">
    $(document).ready(function () {
        $("#getData1").click(function () {
            $.ajax({
                url: 'api-employee?action=list',
                type: 'GET',
                headers:
                    {
                        Accept: "application/json: charset=utf-8",
                        "Content-Type": "application/json: charset=utf-8"
                    },
                success: function (listEmp) {
                    var emp = $.getJSON(listEmp)
                    console.log("success", emp);
                },
                error: function (listEmp) {
                    var emp =  $.getJSON(listEmp)
                    console.log("success", emp);
                }
            });
        });

        $("#getData2").click(function () {
            $.get("api-employee", function(responseJson) {    // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response JSON...
                var $ul = $("<ul>").appendTo($("#somediv")); // Create HTML <ul> element and append it to HTML DOM element with ID "somediv".
                $.each(responseJson, function(index, item) { // Iterate over the JSON array.
                    $("<li>").text(item).appendTo($ul);      // Create HTML <li> element, set its text content with currently iterated item and append it to the <ul>.
                });
            });
        })
    });
</script>