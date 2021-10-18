package services.model.bean;

public class Service {
    private int serviceId;
    private String serviceName;
    private int serviceAre;
    private double serviceCost;
    private int serviceMaxPeople;
    private RentType serviceRentTypeId;
    private ServiceType serviceTypeId;
    private String standardRoom;
    private String description;
    private double poolArea;
    private int numberOfFloor;
    private String serviceCode;

    public Service() {
    }


    public Service(int serviceId, String serviceName, int serviceAre, double serviceCost, int serviceMaxPeople, RentType serviceRentTypeId, ServiceType serviceTypeId, String standardRoom, String description, double poolArea, int numberOfFloor, String serviceCode) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.serviceAre = serviceAre;
        this.serviceCost = serviceCost;
        this.serviceMaxPeople = serviceMaxPeople;
        this.serviceRentTypeId = serviceRentTypeId;
        this.serviceTypeId = serviceTypeId;
        this.standardRoom = standardRoom;
        this.description = description;
        this.poolArea = poolArea;
        this.numberOfFloor = numberOfFloor;
        this.serviceCode = serviceCode;
    }

    public Service(String serviceName, int serviceAre, double serviceCost, int serviceMaxPeople, RentType serviceRentTypeId, ServiceType serviceTypeId, String standardRoom, String description, double poolArea, int numberOfFloor, String serviceCode) {
        this.serviceName = serviceName;
        this.serviceAre = serviceAre;
        this.serviceCost = serviceCost;
        this.serviceMaxPeople = serviceMaxPeople;
        this.serviceRentTypeId = serviceRentTypeId;
        this.serviceTypeId = serviceTypeId;
        this.standardRoom = standardRoom;
        this.description = description;
        this.poolArea = poolArea;
        this.numberOfFloor = numberOfFloor;
        this.serviceCode = serviceCode;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public int getServiceAre() {
        return serviceAre;
    }

    public void setServiceAre(int serviceAre) {
        this.serviceAre = serviceAre;
    }

    public double getServiceCost() {
        return serviceCost;
    }

    public void setServiceCost(double serviceCost) {
        this.serviceCost = serviceCost;
    }

    public int getServiceMaxPeople() {
        return serviceMaxPeople;
    }

    public void setServiceMaxPeople(int serviceMaxPeople) {
        this.serviceMaxPeople = serviceMaxPeople;
    }

    public RentType getServiceRentTypeId() {
        return serviceRentTypeId;
    }

    public void setServiceRentTypeId(RentType serviceRentTypeId) {
        this.serviceRentTypeId = serviceRentTypeId;
    }

    public ServiceType getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(ServiceType serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public String getStandardRoom() {
        return standardRoom;
    }

    public void setStandardRoom(String standardRoom) {
        this.standardRoom = standardRoom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPoolArea() {
        return poolArea;
    }

    public void setPoolArea(double poolArea) {
        this.poolArea = poolArea;
    }

    public int getNumberOfFloor() {
        return numberOfFloor;
    }

    public void setNumberOfFloor(int numberOfFloor) {
        this.numberOfFloor = numberOfFloor;
    }
}
