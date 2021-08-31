<%--
  Created by IntelliJ IDEA.
  User: lottehpt
  Date: 8/21/2021
  Time: 11:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Employees Application</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div>

        <center>
            <h1>Employees Management</h1>
            <h2>
                <a href="api-employee?action=new">Add New Employee</a>
                &nbsp;&nbsp;&nbsp;
                <a href="api-employee?action=list">List All Books</a>

            </h2>
        </center>

    </div>
    <div class="col-9 mx-auto my-5">
        <form id="addEmp">
            <h3>Add new Employee</h3>
            <div class="form-group">
                <label for="empId">Emp Id:</label>
                <input type="text" class="form-control" name="employeeId" id="empId">
            </div>
            <div class="form-group">
                <label for="firstName">First name:</label>
                <input type="text" class="form-control" name="firstName" id="firstName">
            </div>
            <div class="form-group">
                <label for="lastName">Last name:</label>
                <input type="text" class="form-control" name="lastName" id="lastName">
            </div>
            <div class="form-group">
                <label for="email">email:</label>
                <input type="text" class="form-control" name="email" id="email">
            </div>
            <div class="form-group">
                <label for="hireDate">Hire Date:</label>
                <input type="date" class="form-control" name="hireDate" id="hireDate">
            </div>
            <div class="form-group">
                <label for="jobId">Job Id:</label>
                <input type="text" class="form-control" name="jobId" id="jobId">
            </div>
            <div class="form-group">
                <label for="salary">Salary:</label>
                <input type="number" class="form-control" name="salary" id="salary">
            </div>
            <div class="form-group">
                <input type="submit" class="btn btn-primary" value="Submit">
            </div>
        </form>

        <div>
            <p id="displayEmp"></p>
        </div>
    </div>
</div>
</body>
</html>
<script type="text/javascript">
    $(document).ready(function () {
        $('#addEmp').submit(function (event) {
            event.preventDefault();
            console.log("form");
            console.log($(this));
            var empObject = $(this).serializeArray();
            console.log("emob", empObject);
            console.log(typeof empObject);
            var formEmp = JSON.stringify(empObject)
            console.log(JSON.parse(formEmp));
            console.log(typeof formEmp);


            console.log("OBJECT");
            var empFormObject = {};
            $.each(empObject, function (i, v) {
                empFormObject[v.name] = v.value;
            })
            var request = ({"message": 'Hello from browser'});
            var JSONformEmp = JSON.stringify(empFormObject)
            console.log(JSONformEmp);

            $.ajax({
                url: 'api-employee?action=insert',
                type: 'POST',
                dataType: "json",
                data: {para: JSONformEmp},
                success: function (response) {
                    response = JSON.parse(response);
                    if (response.status == 0) {
                        alert("error");
                    } else {
                        alert("oke");
                    }

                }
            });


        })


    })
</script>
