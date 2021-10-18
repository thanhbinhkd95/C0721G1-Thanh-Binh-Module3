package services.controller;

import services.model.bean.RentType;
import services.model.bean.Service;
import services.model.bean.ServiceType;
import services.model.service.Impl.ServiceServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ServiceServlet",urlPatterns = "/services")
public class ServiceServlet extends HttpServlet {
    ServiceServiceImpl serviceService = new ServiceServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action ="";
        }
        switch (action){
            case "create" :
                createServices(request,response);
                break;
            case "edit" :
                editServices(request,response);
                break;
            case "delete" :
                deleteService(request,response);
                break;
        }
    }




    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action ="";
        }
        switch (action){
            case "create" :
                showcreateServices(request,response);
                break;
            case "edit" :
                showEditService(request,response);
                break;

            default:
                showListService(request,response);
                break;
        }
    }




    private void showcreateServices(HttpServletRequest request, HttpServletResponse response) {
        List<ServiceType> serviceTypeList = serviceService.selectAllTypeService();
        request.setAttribute("serviceList",serviceTypeList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/service/create.jsp");
        try {
            requestDispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void createServices(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("service_name");
        int serviceArea = Integer.parseInt(request.getParameter("service_area"));
        double serviceCost = Double.parseDouble(request.getParameter("service_cost"));
        int serviceMaxPeople = Integer.parseInt(request.getParameter("service_max_people"));
        RentType rentTypeId = serviceService.findByRentType(Integer.parseInt(request.getParameter("rent_type_id")));
        ServiceType serviceTypeID =serviceService.findByIdType(Integer.parseInt(request.getParameter("service_type_id")));
        String strandardRoom = request.getParameter("strandard_room");
        String des = request.getParameter("description_other_convenience");
        String pool = request.getParameter("pool_area");
        String number = request.getParameter("number_of_floors");
        double poolArea = 0;
        int numberFloor= 0;
        if(strandardRoom == ""){
            strandardRoom = null;
        }
        if(des == ""){
            des = null;
        }
        if(pool == ""){
            poolArea =0;
        }else {
            poolArea =  Double.parseDouble(request.getParameter("pool_area"));
        }
        if(number == ""){
            numberFloor = 0;
        }else {
            numberFloor =Integer.parseInt(request.getParameter("number_of_floors"));
        }
        String serviceCode = request.getParameter("service_code");

        Service service = new Service(name,serviceArea,serviceCost,serviceMaxPeople,rentTypeId,serviceTypeID,strandardRoom,des,poolArea,numberFloor,serviceCode);
       Map<String,String> msgMap = serviceService.insertService(service);
       if(msgMap.isEmpty()){
           request.setAttribute("message","Message Successfull");
           showListService(request,response);
       }else {
           request.setAttribute("msgCode",msgMap.get("code"));
           try {
               request.getRequestDispatcher("view/service/create.jsp").forward(request,response);
           } catch (ServletException e) {
               e.printStackTrace();
           } catch (IOException e) {
               e.printStackTrace();
           }
       }

    }

    private void showListService(HttpServletRequest request, HttpServletResponse response) {
        List<Service> serviceList = serviceService.selectAllService();
        request.setAttribute("serviceList",serviceList);
        try {
            request.getRequestDispatcher("view/service/list.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    private void showEditService(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        List<ServiceType> serviceTypeList = serviceService.selectAllTypeService();
        List<RentType> rentTypeList = serviceService.selectAllRentType();
        Service service = serviceService.selectService(id);
        request.setAttribute("serviceTypeList",serviceTypeList);
        request.setAttribute("rentTypeList",rentTypeList);
        request.setAttribute("service",service);

        try {
            request.getRequestDispatcher("view/service/edit.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void editServices(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("service_id"));
        String name = request.getParameter("service_name");
        int serviceArea = Integer.parseInt(request.getParameter("service_area"));
        double serviceCost = Double.parseDouble(request.getParameter("service_cost"));
        int serviceMaxPeople = Integer.parseInt(request.getParameter("service_max_people"));
        RentType rentTypeId = serviceService.findByRentType(Integer.parseInt(request.getParameter("rent_type_id")));
        ServiceType serviceTypeID =serviceService.findByIdType(Integer.parseInt(request.getParameter("service_type_id")));
        String strandardRoom = request.getParameter("strandard_room");
        String des = request.getParameter("description_other_convenience");
        String pool = request.getParameter("pool_area");
        String number = request.getParameter("number_of_floors");
        double poolArea = 0;
        int numberFloor= 0;
        if(strandardRoom == ""){
            strandardRoom = null;
        }
        if(des == ""){
            des = null;
        }
        if(pool == ""){
            poolArea =0;
        }else {
            poolArea =  Double.parseDouble(request.getParameter("pool_area"));
        }
        if(number == ""){
            numberFloor = 0;
        }else {
            numberFloor =Integer.parseInt(request.getParameter("number_of_floors"));
        }
        String serviceCode = request.getParameter("service_code");
        Service service = new Service(id,name,serviceArea,serviceCost,serviceMaxPeople,rentTypeId,serviceTypeID,strandardRoom,des,poolArea,numberFloor,serviceCode);
        serviceService.updateService(service);
        try {
            response.sendRedirect("/services");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    private void deleteService(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("service_id"));
        serviceService.deleteService(id);
        try {
            response.sendRedirect("/services");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
