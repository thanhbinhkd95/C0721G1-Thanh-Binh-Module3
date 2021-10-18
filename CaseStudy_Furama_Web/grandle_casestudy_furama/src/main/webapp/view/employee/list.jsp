
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 5/30/2021
  Time: 7:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 5/28/2021
  Time: 3:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Furama Resort</title>
    <link href="../../css/furama_css.css" rel="stylesheet" type="text/css">
    <link href="../../css/base.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="../../datatables/css/dataTables.bootstrap4.min.css" />
    <style>
        .pagination{
            font-size: 15px;
        }
        div.dataTables_wrapper div.dataTables_info {
            padding-top: 0.85em;
            white-space: nowrap;
            font-size: 15px;
        }
        .table td, th {
            text-align: center;}
    </style>
    <script>
        $(document).ready(function(){
            $('[data-toggle="tooltip"]').tooltip();
        });
    </script>
</head>
<body>

<div class="header">
    <div class="row">
        <div class="col-lg-4">
            <img src="../../img/logo@2x.png" height="150">
        </div>
        <div class="col-lg-4">
            <img src="../../img/logo-chinh-thuc.png" width="100%">
        </div>
        <div class="col-lg-4">
            <form class="form-login">
                <button class="btn btn-outline-success" type="button">Login</button>
                <button class="btn  btn-outline-secondary" type="button">Sign up</button>
            </form>
        </div>

    </div>
</div>

<div class="header-link">
    <div class="link-left">
        <ul class="nav__link">
            <li><a href="list.jsp" class="link-control">Home</a></li>
            <li><a href="/employee" class="link-control">Employee</a></li>
            <li><a href="/customer" class="link-control">Customer</a></li>
            <li><a href="/services" class="link-control">Services</a></li>
            <li><a href="/contract" class="link-control">Contact</a></li>
        </ul>
    </div>
    <nav class="navbar navbar-light bg-light">
        <form class="form-inline" style="margin-top: 0px" method="post" action="/employee?action=search">
            <input class="form-control mr-sm-2" style="height: 35px;min-width: 200px" type="search"
                   placeholder="Search" aria-label="Search" name="name">
            <button class="btn btn-outline-success my-2 my-sm-0 bg-primary" type="submit">Search</button>
        </form>
    </nav>
</div>

<div class="container-fluid mt-5 border border-body">
    <div class="text-center">
        <h1>List</h1>
    </div>
    <table class="table table-striped table-bordered" style="width:100%" id="tableStudent">
        <thead class="header">
        <tr>
            <th>ID</th>
            <th>Employee Name</th>
            <th>Birthday</th>
            <th>ID Card</th>
            <th>Phone</th>
            <th>Position</th>

            <td class="text-center"><a href="/employee?action=create" style="font-size: 20px; text-decoration: none"><i class="fas fa-plus-circle"></i>Add</a></td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${employeeList}" var="employees">
            <tr>
                <th>${employees.employeeId}</th>
                <th>${employees.employeeName}</th>
                <th>${employees.employeeBirthday}</th>
                <th>${employees.employeeIdCard}</th>
                <th>${employees.employeePhone}</th>
                <th>${employees.positionId.positionName}</th>
                <td>
                    <a href="/employee?action=edit&id=${employees.employeeId}"
                       class="btn btn-outline-info">Edit</a>
                    <button onclick="setIdDelete(${employees.employeeId})" type="button" class="btn btn-outline-danger" data-toggle="modal" data-target="#exampleModal">
                        Delete
                    </button>
                    <div class="modal fade" id="exampleModal" tabindex="1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <form action="/employee?action=delete" method="post">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">DELETE</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        Are you sure you want to delete it?
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                        <input type="submit" class="btn btn-danger btn-ok" id="delete" value="Delete">
                                        <input type="hidden" id="employee_id" name="employee_id">
                                    </div>
                                </div>
                            </form>
                        </div>

                    </div>
                </td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


<script src="../../jquery/jquery-3.5.1.min.js"></script>
<script src="../../datatables/js/jquery.dataTables.min.js"></script>
<script src="../../datatables/js/dataTables.bootstrap4.min.js"></script>
<script>
    $(document).ready(function() {
        $('#tableStudent').dataTable( {
            "dom": 'lrtip',
            "lengthChange": false,
            "pageLength": 5
        } );
    } );
    function setIdDelete(id) {
        document.getElementById("employee_id").value = id
    }
</script>
</body>
</html>
