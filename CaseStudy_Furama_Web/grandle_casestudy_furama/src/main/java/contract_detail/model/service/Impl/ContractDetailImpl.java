package contract_detail.model.service.Impl;

import contract_detail.model.bean.AttachService;
import contract_detail.model.bean.ContractDetails;
import contract_detail.model.repository.AttachServiceDetailRepository;
import contract_detail.model.repository.ContractDetailRepository;
import contract_detail.model.service.IContractDetail;
import customers.model.bean.Customers;
import customers.model.service.Impl.CustomerServiceImpl;

import java.util.List;

public class ContractDetailImpl implements IContractDetail {
    AttachServiceDetailRepository attachServiceDetailRepository = new AttachServiceDetailRepository();
    ContractDetailRepository contractDetailRepository = new ContractDetailRepository();


    @Override
    public void insertContactDetail(ContractDetails contractDetails) {
            contractDetailRepository.insertContactDetail(contractDetails);
    }

    @Override
    public List<AttachService> selectAllAttach() {
        return attachServiceDetailRepository.selecAlltAttach();
    }

    @Override
    public AttachService selectAttach(int id) {
        return attachServiceDetailRepository.selectAttach(id);
    }


}
