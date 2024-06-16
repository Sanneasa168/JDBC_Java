package model.service;

import model.dto.CreateProductDto;
import model.dto.ProductDto;
import model.entity.Product;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();
    List<ProductDto>  addNewProduct(CreateProductDto createProductDto);
    List<ProductDto> deleteProduct(Integer id);
}
