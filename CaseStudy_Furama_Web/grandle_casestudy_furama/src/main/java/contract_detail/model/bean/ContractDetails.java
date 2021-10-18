package contract_detail.model.bean;

import contract.model.bean.Contract;

public class ContractDetails {
    private int contractDetail;
    private Contract contractId;
    private AttachService attachSerciceId;
    private int quantity;

    public ContractDetails() {
    }

    public ContractDetails(int contractDetail, Contract contractId, AttachService attachSerciceId, int quantity) {
        this.contractDetail = contractDetail;
        this.contractId = contractId;
        this.attachSerciceId = attachSerciceId;
        this.quantity = quantity;
    }

    public ContractDetails(Contract contractId, AttachService attachSerciceId, int quantity) {
        this.contractId = contractId;
        this.attachSerciceId = attachSerciceId;
        this.quantity = quantity;
    }

    public int getContractDetail() {
        return contractDetail;
    }

    public void setContractDetail(int contractDetail) {
        this.contractDetail = contractDetail;
    }

    public Contract getContractId() {
        return contractId;
    }

    public void setContractId(Contract contractId) {
        this.contractId = contractId;
    }

    public AttachService getAttachSerciceId() {
        return attachSerciceId;
    }

    public void setAttachSerciceId(AttachService attachSerciceId) {
        this.attachSerciceId = attachSerciceId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
