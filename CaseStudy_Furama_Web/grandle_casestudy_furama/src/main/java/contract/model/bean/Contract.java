package contract.model.bean;

import contract_detail.model.bean.AttachService;
import contract_detail.model.bean.ContractDetails;
import customers.model.bean.Customers;
import employee.model.bean.Employee;
import services.model.bean.Service;

public class Contract {
    private int contractId;
    private String contractStartDate;
    private String contractEndDate;
    private double deposit;
    private double totalMoney;
    private Employee employeeId;
    private Customers customersId;
    private Service serviceId;
    private AttachService attachServiceid;

    public Contract(int contractId, String contractStartDate, String contractEndDate, Customers customersId, AttachService attachServiceid) {
        this.contractId = contractId;
        this.contractStartDate = contractStartDate;
        this.contractEndDate = contractEndDate;
        this.customersId = customersId;
        this.attachServiceid = attachServiceid;

    }

    public AttachService getAttachServiceid() {
        return attachServiceid;
    }

    public void setAttachServiceid(AttachService attachServiceid) {
        this.attachServiceid = attachServiceid;
    }

    public Contract() {
    }

    public Contract(int contractId, String contractStartDate, String contractEndDate, double deposit, double totalMoney, Employee employeeId, Customers customersId, Service serviceId) {
        this.contractId = contractId;
        this.contractStartDate = contractStartDate;
        this.contractEndDate = contractEndDate;
        this.deposit = deposit;
        this.totalMoney = totalMoney;
        this.employeeId = employeeId;
        this.customersId = customersId;
        this.serviceId = serviceId;
    }

    public Contract(String contractStartDate, String contractEndDate, double deposit, double totalMoney, Employee employeeId, Customers customersId, Service serviceId) {
        this.contractStartDate = contractStartDate;
        this.contractEndDate = contractEndDate;
        this.deposit = deposit;
        this.totalMoney = totalMoney;
        this.employeeId = employeeId;
        this.customersId = customersId;
        this.serviceId = serviceId;
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

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }

    public Customers getCustomersId() {
        return customersId;
    }

    public void setCustomersId(Customers customersId) {
        this.customersId = customersId;
    }

    public Service getServiceId() {
        return serviceId;
    }

    public void setServiceId(Service serviceId) {
        this.serviceId = serviceId;
    }
}
