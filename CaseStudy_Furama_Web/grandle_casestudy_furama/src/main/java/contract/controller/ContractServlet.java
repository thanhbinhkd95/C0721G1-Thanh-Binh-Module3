package contract.controller;

import common.Validate;
import contract.model.bean.Contract;
import contract.model.bean.CustomerUseService;
import contract.model.service.Impl.ContractServiceImpl;
import customers.model.bean.Customers;
import customers.model.service.Impl.CustomerServiceImpl;
import employee.model.bean.Employee;
import employee.model.service.Impl.EmployeeServiceImpl;
import services.model.bean.Service;
import services.model.service.Impl.ServiceServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ContractServlet",urlPatterns = "/contract")
public class ContractServlet extends HttpServlet {
    ContractServiceImpl contractService = new ContractServiceImpl();
    EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
    CustomerServiceImpl customerService = new CustomerServiceImpl();
    ServiceServiceImpl serviceService = new ServiceServiceImpl();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createContract(request, response);
                break;
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showCreateContract(request, response);
                break;
            case "customerUse":
                showCustomerUseService(request,response);
                break;
            case "list" :
                showListContract(request,response);
                break;
        }
    }




    private void showCreateContract(HttpServletRequest request, HttpServletResponse response) {
        List<Employee> employeeList = employeeService.selectAllEmployee();
        List<Customers> customersList = customerService.selectAllCustomers();
        List<Service> serviceList = serviceService.selectAllService();
        request.setAttribute("employeeList",employeeList);
        request.setAttribute("customersList",customersList);
        request.setAttribute("serviceList",serviceList);

        try {
            request.getRequestDispatcher("/view/contract/create.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void createContract(HttpServletRequest request, HttpServletResponse response) {
        String contactStart = request.getParameter("contract_start_date");
//        String messageStartDate = Validate.regexDate(contactStart);
        String contactEnd = request.getParameter("contract_end_date");
//        String messageEndDate = Validate.regexDate(contactEnd);
        double deposit = Double.parseDouble(request.getParameter("contract_deposit"));
        double total = Double.parseDouble(request.getParameter("contract_total_money"));
        Employee employeeId = employeeService.selectEmployee(Integer.parseInt(request.getParameter("employee_id")));
        Customers customersId = customerService.selectCustomer(Integer.parseInt(request.getParameter("customer_id")));
        Service serviceId = serviceService.selectService(Integer.parseInt(request.getParameter("service_id")));
        Contract contract = new Contract(contactStart,contactEnd,deposit,total,employeeId,customersId,serviceId);
            contractService.insertContract(contract);
            try {
                request.getRequestDispatcher("/view/contract/create.jsp").forward(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }




    private void showListContract(HttpServletRequest request, HttpServletResponse response) {
        List<Contract> contractList = contractService.selectAllContract();
        request.setAttribute("contractList",contractList);
        try {
            request.getRequestDispatcher("view/contract/list.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void showCustomerUseService(HttpServletRequest request, HttpServletResponse response) {
        List<CustomerUseService> customerUseServiceList = contractService.selectAllCustomersUseService();
        request.setAttribute("customerUseServiceList",customerUseServiceList);
        try {
            request.getRequestDispatcher("view/contract/customer_use_services.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
