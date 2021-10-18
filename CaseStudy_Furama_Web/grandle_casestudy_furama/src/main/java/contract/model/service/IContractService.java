package contract.model.service;

import contract.model.bean.Contract;
import contract.model.bean.CustomerUseService;
import contract_detail.model.bean.AttachService;
import customers.model.bean.Customers;

import java.util.List;

public interface IContractService {
    void insertContract(Contract contract);
    Contract selectContract(int id);
    List<Contract> selectAllContract();
    List<AttachService> selectAllAttach();
    List<Customers> selectAllCustomers();
    List<CustomerUseService> selectAllCustomersUseService();
}
