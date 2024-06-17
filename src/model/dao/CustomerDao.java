package model.dao;

import model.dto.CreateCustomerDto;
import model.entity.Customer;

import java.util.List;

public interface CustomerDao {
    List<Customer> queryAllCustomers();
    int deleteCustomerById(Integer id);
    int updateCusttomerById(Integer id);
    int addNewCustomer(CreateCustomerDto customer);
    Customer  searchCustomerById(Integer id);

}
