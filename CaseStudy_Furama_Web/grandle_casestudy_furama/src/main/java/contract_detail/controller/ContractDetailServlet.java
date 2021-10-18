package contract_detail.controller;

import contract.model.bean.Contract;
import contract.model.service.Impl.ContractServiceImpl;
import contract_detail.model.bean.AttachService;
import contract_detail.model.bean.ContractDetails;
import contract_detail.model.service.Impl.ContractDetailImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ContractDetailServlet",urlPatterns = "/contractdetail")
public class ContractDetailServlet extends HttpServlet {
    ContractDetailImpl contractDetail = new ContractDetailImpl();
    ContractServiceImpl contractService = new ContractServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action ="";
        }
        switch (action){
            case "create" :
                createContractDetailForm(request,response);
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
        }
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) {
        List<Contract> contractList = contractService.selectAllContract();
        List<AttachService> attachList = contractDetail.selectAllAttach();
        request.setAttribute("contractList",contractList);
        request.setAttribute("attachList",attachList);
        try {
            request.getRequestDispatcher("view/contract_detail/create.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    private void createContractDetailForm(HttpServletRequest request, HttpServletResponse response) {
        Contract idContract = contractService.selectContract(Integer.parseInt(request.getParameter("contract_id")));
        AttachService attachServiceId = contractDetail.selectAttach(Integer.parseInt(request.getParameter("attach_service_id")));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        ContractDetails contractDetails = new ContractDetails(idContract,attachServiceId,quantity);
        contractDetail.insertContactDetail(contractDetails);
        try {
            response.sendRedirect("view/main_page.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
