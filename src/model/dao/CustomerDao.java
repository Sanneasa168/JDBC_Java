package model.dao;

import model.entity.Customer;

import java.util.List;

public interface CustomerDao {
    List<Customer> queryAllCustomers();
    int deleteCustomerById(Integer id);
    int updateCusttomerById(Integer id);
    int addNewCustomer(Customer customer);
    Customer  searchCustomerById(Integer id);

}
