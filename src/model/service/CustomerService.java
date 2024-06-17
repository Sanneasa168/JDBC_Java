package model.service;

import model.dao.CustomerDao;
import model.dto.CreateCustomerDto;
import model.dto.CreateProductDto;
import model.dto.CustomerDto;
import model.dto.ProductDto;

import java.util.List;

public interface CustomerService {
    List<CustomerDto> getAllCustomers();
    List<CustomerDto>  addNewProduct(CreateCustomerDto createCustomerDto);
    List<CustomerDto> deleteProduct(Integer id);
}
