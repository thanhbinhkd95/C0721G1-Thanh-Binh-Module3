<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link href="../css/furama_css.css" rel="stylesheet" type="text/css">
    <link href="../css/base.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
    <script>

        $(document).ready(function(){
            // Activate tooltip
            $('[data-toggle="tooltip"]').tooltip();
        });
    </script>
</head>
<body>

<div class="header">
    <div class="row">
        <div class="col-lg-4">
            <img src="../img/logo@2x.png" height="150">
        </div>
        <div class="col-lg-4">
            <img src="../img/logo-chinh-thuc.png" width="100%">
        </div>
        <div class="col-lg-4">
            <form class="form-login">
                <button class="btn btn-outline-success" type="button">Login</button>
                <button class="btn  btn-outline-secondary" type="button">Sign up</button>
            </form>
        </div>

    </div>
</div>



<div class="container mt-5 border border-body">
    <div class="text-center">
        <h1>List</h1>
    </div>
    <table class="table ">
        <thead class="header">
        <tr>
            <th>ID</th>
            <th>Họ Tên</th>
            <th>Ngày Sinh</th>
            <th>Số CMND</th>
            <th>Phone</th>
            <th>Email</th>
            <th>Địa Chỉ</th>
            <th>Loại Khách</th>
            <td><a href="" style="font-size: 20px; text-decoration: none"><i class="fas fa-plus-circle"></i>Add</a></td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="listCustomer" items="${listCustomers}">
        <tr>
            <th>${listCustomer.id}</th>
            <th>${listCustomer.name}</th>
            <th>${listCustomer.dayOfBirth}</th>
            <th>${listCustomer.idCard}</th>
            <th>${listCustomer.phone}</th>
            <th>${listCustomer.email}</th>
            <th>${listCustomer.address}</th>
            <th>${listCustomer.customerType}</th>
            <td>
                <a href="edit.jsp" class="edit" ><i class="far fa-edit" style="font-size: 30px;color: red" ></i></a>
                <a href="#deleteEmployeeModal" class="delete" data-toggle="modal"><i data-toggle="tooltip" class="fas fa-trash-alt mb-1 style=" style="font-size: 30px; color: red" title="Delete">&#xE872;</i></a>
            </td>

        </tr>
        </c:forEach>
        </tbody>
    </table>
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-end">
            <li class="page-item disabled">
                <a class="page-link" href="#" tabindex="-1">Previous</a>
            </li>
            <li class="page-item"><a class="page-link" href="#">1</a></li>
            <li class="page-item"><a class="page-link" href="#">2</a></li>
            <li class="page-item"><a class="page-link" href="#">3</a></li>
            <li class="page-item">
                <a class="page-link" href="#">Next</a>

            </li>
        </ul>
    </nav>
</div>
<div id="deleteEmployeeModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form>
                <div class="modal-header">
                    <h4 class="modal-title">Delete</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <p>Are you sure you want to delete these Records?</p>
                    <p class="text-warning"><small>This action cannot be undone.</small></p>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                    <input type="submit" class="btn btn-danger" value="Delete">
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
