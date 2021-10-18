package contract_detail.model.service;

import contract_detail.model.bean.AttachService;
import contract_detail.model.bean.ContractDetails;
import customers.model.bean.Customers;

import java.util.List;

public interface IContractDetail {
    void insertContactDetail(ContractDetails contractDetails);
     List<AttachService> selectAllAttach();
     AttachService selectAttach(int id);

}
