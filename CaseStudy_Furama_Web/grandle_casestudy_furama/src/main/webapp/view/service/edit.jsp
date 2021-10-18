<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 5/29/2021
  Time: 7:36 PM
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Title</title>
    <link href="../../css/base.css" rel="stylesheet" type="text/css">
    <link href="../../css/furama_css.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>

    <style>
        body {
            margin: 0;
            /*padding-top: 40px;*/
            color: #2e323c;
            background: #f5f6fa;
            position: relative;
            height: 100%;
        }

        label {
            font-size: 20px;
        }

        .row__start {
            text-align: start;
        }

        .account-settings .user-profile {
            margin: 0 0 1rem 0;
            padding-bottom: 1rem;
            text-align: center;
        }

        .account-settings .user-profile .user-avatar {
            margin: 0 0 1rem 0;
        }

        .account-settings .user-profile .user-avatar img {
            width: 90px;
            height: 90px;
            -webkit-border-radius: 100px;
            -moz-border-radius: 100px;
            border-radius: 100px;
        }

        .account-settings .user-profile h5.user-name {
            margin: 0 0 0.5rem 0;
        }

        .account-settings .user-profile h6.user-email {
            margin: 0;
            font-size: 3rem;
            font-weight: 400;
            color: #9fa8b9;
        }

        .account-settings .about {
            margin: 2rem 0 0 0;
            text-align: center;
        }

        .account-settings .about h5 {
            margin: 0 0 15px 0;
            color: #007ae1;
        }

        .account-settings .about p {
            font-size: 1rem;
        }

        .form-control {
            border: 1px solid #cfd1d8;
            -webkit-border-radius: 2px;
            -moz-border-radius: 2px;
            border-radius: 2px;
            font-size: 2rem;
            background: #ffffff;
            color: #2e323c;
        }

        .card {
            background: #ffffff;
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;
            border: 0;
            margin-bottom: 1rem;
        }

    </style>
</head>
<body onload="editFromService(${service.serviceTypeId.serviceTypeId})">
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
                <button class="btn btn-outline-secondary" type="button">Sign up</button>
            </form>
        </div>
    </div>
</div>


<div class="container mt-5">
    <div class="row gutters row__start">
        <div class="col-xl-12">
            <h1 id="title">
                Edit ${service.serviceTypeId.serviceTypeName}
            </h1>
        </div>
        <div class="col-xl-12 col-lg-9 col-md-12 col-sm-12 col-12">
            <div class="card h-100">
                <form method="post">
                    <div class="card-body">
                        <div class="row gutters row__start">
                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                <h3 class="mb-2 text-primary">ADD SERVICES</h3>
                            </div>
                            <div class="col-xl-12 col-lg-6 col-md-6 col-sm-6 col-12">
                                <c:if test="${service != null}">
                                    <input type="hidden" name="service_id" value="${service.serviceId}"/>
                                </c:if>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label>Name Type Services</label>
                                    <select class="custom-select" name="service_type_id" onchange="editFromService(this.value)" style="font-size: 2rem;height: 40px">
                                        <c:forEach items="${serviceTypeList}" var="typeList">
                                            <option value="${typeList.serviceTypeId}" ${service.serviceTypeId.serviceTypeId == typeList.serviceTypeId?"selected":""} >${typeList.serviceTypeName}</option>
                                        </c:forEach>
                                    </select>

                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label>Name Services</label>
                                    <input value="${service.serviceName}" name="service_name" type="text" class="form-control"
                                           placeholder="Name Services">
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label>Service Code</label>
                                    <input value="${service.serviceCode}" name="service_code" type="text" class="form-control"
                                           placeholder="Name Services">
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label>Service Area</label>
                                    <input value="${service.serviceAre}" name="service_area" type="text" class="form-control"
                                           placeholder="Service Area">
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label>Service Cost</label>
                                    <input value="${service.serviceCost}" name="service_cost" type="text" class="form-control"
                                           placeholder="Service Cost">
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label>Max People</label>
                                    <input value="${service.serviceMaxPeople}" name="service_max_people" type="text" class="form-control"
                                           placeholder="Max People">
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label>Rent Type</label>
                                    <select class="custom-select" name="rent_type_id"
                                            style="font-size: 2rem;height: 40px">
                                       <c:forEach items="${rentTypeList}" var="rentType">
                                           <option value="${rentType.rentTypeId}" ${service.serviceRentTypeId.rentTypeId == rentType.rentTypeId ?"selected":"" }>${rentType.rentTypeName}</option>
                                       </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="row gutters row__start">
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12" id="standard_room">
                                <div class="form-group">
                                    <label>Standard Room</label>
                                    <input value="${service.standardRoom}"  name="strandard_room" type="text" class="form-control"
                                            placeholder="Enter Standard Room">
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12" id="description_other_convenience">
                                <div class="form-group">
                                    <label>Description</label>
                                    <input  value="${service.description}" type="text" class="form-control" name="description_other_convenience"
                                            placeholder="Enter Description">
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12" id="pool_area">
                                <div class="form-group">
                                    <label>Pool Area</label>
                                    <input value="${service.poolArea}" type="text" class="form-control" name="pool_area"
                                           placeholder="Enter Pool Area">
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12" id="number_of_floors" >
                                <div class="form-group">
                                    <label>Number of Floor</label>
                                    <input value="${service.numberOfFloor}" type="text" class="form-control" name="number_of_floors"
                                           placeholder="Enter Number of Floor">
                                </div>
                            </div>
                        </div>
                        <div class="row gutters row__start">
                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                <div class="text-center">
                                    <a type="button" href="customer?action=customers"
                                       class="btn btn-outline-secondary">Close</a>
                                    <input type="submit" value="Save" class="btn btn-outline-success"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    function editFromService(x) {
        if(x == 1){
            document.getElementById("standard_room").style.display = "block";
            document.getElementById("description_other_convenience").style.display = "block";
            document.getElementById("pool_area").style.display = "block";
            document.getElementById("number_of_floors").style.display = "block";
            document.getElementById("title").innerText = "EDIT VILLA";

        }
        if(x == 2){
            document.getElementById("pool_area").style.display = "none";
            document.getElementById("title").innerText = "EDIT HOUSE";
        }
        if(x == 3){
            document.getElementById("standard_room").style.display = "none";
            document.getElementById("description_other_convenience").style.display = "none";
            document.getElementById("pool_area").style.display = "none";
            document.getElementById("number_of_floors").style.display = "none";
            document.getElementById("title").innerText = "EDIT ROOM";
        }
    }
</script>

</body>
</html>

