package controller;

import model.dto.CreateCustomerDto;
import model.dto.CreateProductDto;
import model.dto.CustomerDto;
import model.dto.ProductDto;
import model.service.CustomerService;
import model.service.CustomerServiceImpl;

import java.util.List;

public class CustomerController {
    final CustomerService customerService = new CustomerServiceImpl();
    public List<CustomerDto> getAllProducts() {
        return customerService.getAllCustomers();
    }
    public List<CustomerDto> addNewCustomer(CreateCustomerDto createCustomerDto) {
        return  customerService.addNewProduct(createCustomerDto);
    }
    public List<CustomerDto> deletedCustomer(Integer id) {
        return customerService.deleteProduct(id);
    }

}
