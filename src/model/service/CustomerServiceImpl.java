package model.service;

import mapper.CustomerMapper;
import mapper.ProductMapper;
import model.dao.CustomerDao;
import model.dao.CustomerDaoImpl;
import model.dao.ProductDao;
import model.dao.ProductDaoImpl;
import model.dto.CreateCustomerDto;
import model.dto.CreateProductDto;
import model.dto.CustomerDto;
import model.dto.ProductDto;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerServiceImpl implements CustomerService{
//    private final ProductDao productDao = new ProductDaoImpl();
    private final CustomerDao  customerDao = new CustomerDaoImpl();
    @Override
    public List<CustomerDto> getAllCustomers() {
        return  customerDao.queryAllCustomers()
                .stream()
                .map(CustomerMapper::fromCustomerToCustomerDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerDto> addNewProduct(CreateCustomerDto createCustomerDto) {
         customerDao.addNewCustomer(createCustomerDto);
        return List.of();
    }

    @Override
    public List<CustomerDto> deleteProduct(Integer id) {
        customerDao.deleteCustomerById(id);
        return List.of();
    }
}
