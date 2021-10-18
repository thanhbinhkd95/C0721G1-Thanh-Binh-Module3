package services.model.service;

import services.model.bean.RentType;
import services.model.bean.Service;
import services.model.bean.ServiceType;

import java.util.List;
import java.util.Map;

public interface IServiceService {
    Map<String,String> insertService(Service service);

    public ServiceType findByIdType(int id);

    public RentType findByRentType(int id);
     List<Service> selectAllService();
    public Service selectService(int id);
    public List<ServiceType> selectAllTypeService();
    boolean updateService(Service service);
    List<RentType> selectAllRentType();
    boolean deleteService(int id);
}
