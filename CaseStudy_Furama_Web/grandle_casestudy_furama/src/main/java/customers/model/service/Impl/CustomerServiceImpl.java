package customers.model.service.Impl;

import common.Validate;
import customers.model.bean.CustomerType;
import contract.model.bean.CustomerUseService;
import customers.model.bean.Customers;
import customers.model.repository.CustomerRepository;
import customers.model.repository.CustomerTypeRepository;
import customers.model.service.ICustomerServices;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerServiceImpl implements ICustomerServices {
    CustomerRepository customerRepository = new CustomerRepository();
    CustomerTypeRepository customerTypeRepository = new CustomerTypeRepository();

    @Override
    public List<Customers> selectAllCustomers() {
        return customerRepository.selectAllCustomers();
    }

    @Override
    public Map<String,String> insertCustomer(Customers customers) throws SQLException {
        Map<String,String> mapMsg = new HashMap<>();
        boolean check = true;
        if(!Validate.regexCodeCustomer(customers.getCustomerCode())){
            check = false;
            mapMsg.put("code","Invalid code KH-XXXX");
        }
        if(!Validate.regexPhone(customers.getCustomerPhone())){
            check = false;
            mapMsg.put("phone","Invalid phone  090xxxxxxx or 091xxxxxxx or (84)+90xxxxxxx or (84)+91xxxxxxx");
        }
        if(!Validate.regexIdCard(customers.getCustomerIdCard())){
            check = false;
            mapMsg.put("idcard","Invalid idcard XXXXXXXXX or XXXXXXXXXXXX (X is a number 0-9)");
        }
        if(!Validate.regexEmail(customers.getCustomerEmail())){
            check = false;
            mapMsg.put("email","Invalid email xxx@xxx.xxx");
        } if(!Validate.regexEmail(customers.getCustomerEmail())){
            check = false;
            mapMsg.put("birthday","Invalid birthday DD/MM/YYYY");
        }
        if(check){
            customerRepository.insertCustomer(customers);
        }
        return mapMsg;
    }

    @Override
    public CustomerType selectCustomerType(int id) {
      return customerTypeRepository.selectCustomerType(id);
    }

    @Override
    public boolean deleteCustomer(int id) {
        return customerRepository.deleteCustomer(id);
    }

    @Override
    public Map<String,String> updateCustomer(Customers customers) {
        Map<String,String> mapMsg = new HashMap<>();
        boolean check = true;
        if(!Validate.regexCodeCustomer(customers.getCustomerCode())){
            check = false;
            mapMsg.put("code","Invalid code KH-XXXX");
        }
        if(!Validate.regexPhone(customers.getCustomerPhone())){
            check = false;
            mapMsg.put("phone","Invalid phone  090xxxxxxx or 091xxxxxxx or (84)+90xxxxxxx or (84)+91xxxxxxx");
        }
        if(!Validate.regexIdCard(customers.getCustomerIdCard())){
            check = false;
            mapMsg.put("idcard","Invalid idcard XXXXXXXXX or XXXXXXXXXXXX (X is a number 0-9)");
        }
        if(!Validate.regexEmail(customers.getCustomerEmail())){
            check = false;
            mapMsg.put("email","Invalid email xxx@xxx.xxx");
        } if(!Validate.regexEmail(customers.getCustomerEmail())){
            check = false;
            mapMsg.put("birthday","Invalid birthday DD/MM/YYYY");
        }
        if(check){
            customerRepository.updateCustomer(customers);
        }
        return mapMsg;
    }

    @Override
    public Customers selectCustomer(int id) {
        return customerRepository.selectCustomer(id);
    }

    @Override
    public List<Customers> searchByName(String name) {
        return customerRepository.searchByName(name);
    }

    @Override
    public List<CustomerType> selectAllCustomerType() {
        return customerTypeRepository.selectAllCustomerType();
    }


}
