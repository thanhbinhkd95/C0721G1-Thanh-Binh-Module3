package customers.controller;

import common.Validate;
import customers.model.bean.CustomerType;
import contract.model.bean.CustomerUseService;
import customers.model.bean.Customers;
import customers.model.service.ICustomerServices;
import customers.model.service.Impl.CustomerServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "CustomersServlet",urlPatterns = {"/customer"})
public class CustomersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ICustomerServices customerServices;

    @Override
    public void init() throws ServletException {
        customerServices = new CustomerServiceImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action ="";
        }
        switch (action){
            case "create" :
                createNewCustomer(request,response);
                break;
            case "delete" :
                deleteCustomer(request,response);
                break;
            case "edit" :
                editCustomer(request,response);
                break;
            case "search" :
                searchByName(request,response);
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
                showCreateForm(request,response);
                break;
            case "edit" :
                showEditPageCustomer(request,response);
            case "customerUse":
                break;
            default:
                listCustomer(request,response);
                break;
        }
    }




    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            List<CustomerType> customerTypeList = customerServices.selectAllCustomerType();
            request.setAttribute("typeList",customerTypeList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("view/customer/create.jsp");
            dispatcher.forward(request,response);

    }

    private void listCustomer(HttpServletRequest request, HttpServletResponse response){
        List<Customers> customersList = customerServices.selectAllCustomers();
        request.setAttribute("listCustomers",customersList);
        try {
            request.getRequestDispatcher("view/customer/list.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void createNewCustomer(HttpServletRequest request, HttpServletResponse response) {
        CustomerType id_type = customerServices.selectCustomerType(Integer.parseInt(request.getParameter("customer_type_id")));
        String name = request.getParameter("customer_name");
        String dayOfBirth = request.getParameter("customer_birthday");
        String gender = request.getParameter("customer_gender");
        String idCard = request.getParameter("customer_id_card");
        String phone = request.getParameter("customer_phone");
        String email = request.getParameter("customer_email");
        String address = request.getParameter("customer_address");
        String code = request.getParameter("customer_code");
        Customers customers = new Customers(id_type,name,dayOfBirth,gender,idCard,phone,email,address,code);
        List<CustomerType> list = customerServices.selectAllCustomerType();
        try {
            Map<String,String> mapMsg = customerServices.insertCustomer(customers);
            if(mapMsg.isEmpty()){
                request.setAttribute("message","Successfull");
                listCustomer(request, response);
            }else {
                request.setAttribute("customerInfo",customers);
                request.setAttribute("typeList",list);
                request.setAttribute("msgCode",mapMsg.get("code"));
                request.setAttribute("msgPhone",mapMsg.get("phone"));
                request.setAttribute("msgIdcard",mapMsg.get("idcard"));
                request.setAttribute("msgEmail",mapMsg.get("email"));
                request.setAttribute("msgBirthday",mapMsg.get("birthday"));
                request.getRequestDispatcher("view/customer/create.jsp").forward(request,response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("customer_id"));
        customerServices.deleteCustomer(id);
        try {
            response.sendRedirect("/customer");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showEditPageCustomer(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        List<CustomerType> list = customerServices.selectAllCustomerType();
        Customers customers =  customerServices.selectCustomer(id);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/customer/edit.jsp");
        request.setAttribute("customers",customers);
        request.setAttribute("typeList",list);
        try {
            requestDispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void editCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("customers_id"));
        CustomerType id_type = customerServices.selectCustomerType(Integer.parseInt(request.getParameter("customer_type_id")));
        String name = request.getParameter("customer_name");
        String dayOfBirth = request.getParameter("customer_birthday");
        String gender = request.getParameter("customer_gender");
        String idCard = request.getParameter("customer_id_card");
        String phone = request.getParameter("customer_phone");
        String email = request.getParameter("customer_email");
        String address = request.getParameter("customer_address");
        String code = request.getParameter("customer_code");
        Customers customers = new Customers(id, id_type, name, dayOfBirth, gender, idCard, phone, email, address, code);
        Map<String, String> mapMsg = customerServices.updateCustomer(customers);
        List<CustomerType> list = customerServices.selectAllCustomerType();
        if (mapMsg.isEmpty()) {
            request.setAttribute("message", "Successfull");
            listCustomer(request, response);

        } else {
            request.setAttribute("customers", customers);
            request.setAttribute("typeList", list);
            request.setAttribute("msgCode", mapMsg.get("code"));
            request.setAttribute("msgPhone", mapMsg.get("phone"));
            request.setAttribute("msgIdcard", mapMsg.get("idcard"));
            request.setAttribute("msgEmail", mapMsg.get("email"));
            request.setAttribute("msgBirthday", mapMsg.get("birthday"));
            request.getRequestDispatcher("view/customer/edit.jsp").forward(request, response);
            try {
                response.sendRedirect("/customer");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void searchByName(HttpServletRequest request, HttpServletResponse response) {
        String name  = request.getParameter("name");
        List<Customers> customersList = customerServices.searchByName(name);
        request.setAttribute("listCustomers",customersList);
        try {
            request.getRequestDispatcher("view/customer/list.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
