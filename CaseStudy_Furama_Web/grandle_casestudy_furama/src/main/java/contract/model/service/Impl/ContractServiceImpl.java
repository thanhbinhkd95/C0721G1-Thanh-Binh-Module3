package contract.model.service.Impl;

import contract.model.bean.Contract;
import contract.model.bean.CustomerUseService;
import contract.model.repository.ContractRepository;
import contract.model.service.IContractService;
import contract_detail.model.bean.AttachService;
import contract_detail.model.service.Impl.ContractDetailImpl;
import customers.model.bean.Customers;
import customers.model.service.Impl.CustomerServiceImpl;

import java.util.List;

public class ContractServiceImpl implements IContractService {
    ContractRepository contractRepository = new ContractRepository();
    ContractDetailImpl contractDetail = new ContractDetailImpl();
    CustomerServiceImpl customerService = new CustomerServiceImpl();
    @Override
    public void insertContract(Contract contract) {
        contractRepository.insertContract(contract);
    }

    @Override
    public Contract selectContract(int id) {
        return contractRepository.selectContract(id);
    }

    @Override
    public List<Contract> selectAllContract() {
        return contractRepository.selectAllContract();
    }


    @Override
    public List<AttachService> selectAllAttach() {
        return contractDetail.selectAllAttach();
    }

    @Override
    public List<Customers> selectAllCustomers() {
        return customerService.selectAllCustomers();
    }

    @Override
    public List<CustomerUseService> selectAllCustomersUseService() {
        return contractRepository.selectAllCustomersUseService();
    }
}
