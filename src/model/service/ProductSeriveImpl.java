package model.service;

import mapper.ProductMapper;
import model.dao.ProductDao;
import model.dao.ProductDaoImpl;
import model.dto.CreateProductDto;
import model.dto.ProductDto;
import model.entity.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductSeriveImpl implements ProductService{
    private final ProductDao productDao = new ProductDaoImpl();
    @Override
    public List<ProductDto> getAllProducts() {
        return productDao.queryAllProducts()
                .stream()
                .map(ProductMapper::fromProductToProductDto)
                .collect(Collectors.toList());

    }
    @Override
    public List<ProductDto> addNewProduct(CreateProductDto createProductDto) {
        productDao.addNewProduct(createProductDto);
        return List.of();
    }

    @Override
    public List<ProductDto> deleteProduct(Integer id) {
        productDao.deleteProduct(id);
        return List.of();
    }
}
