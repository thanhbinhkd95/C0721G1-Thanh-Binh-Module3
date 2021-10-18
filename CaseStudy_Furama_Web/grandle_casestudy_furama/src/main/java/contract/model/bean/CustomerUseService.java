package contract.model.bean;

public class CustomerUseService {
    private int customerId;
    private String customerName;
    private int contractId;
    private String contractStartDate;
    private String contractEndDate;
    private int quantity;
    private int attchServiceId;
    private String attachServiceName;

    public CustomerUseService() {
    }

    public CustomerUseService(int customerId, String customerName, int contractId, String contractStartDate, String contractEndDate, int quantity, int attchServiceId, String attachServiceName) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.contractId = contractId;
        this.contractStartDate = contractStartDate;
        this.contractEndDate = contractEndDate;
        this.quantity = quantity;
        this.attchServiceId = attchServiceId;
        this.attachServiceName = attachServiceName;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public String getContractStartDate() {
        return contractStartDate;
    }

    public void setContractStartDate(String contractStartDate) {
        this.contractStartDate = contractStartDate;
    }

    public String getContractEndDate() {
        return contractEndDate;
    }

    public void setContractEndDate(String contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getAttachServiceName() {
        return attachServiceName;
    }

    public void setAttachServiceName(String attachServiceName) {
        this.attachServiceName = attachServiceName;
    }
}
