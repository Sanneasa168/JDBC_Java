package controller;

import model.dto.CreateProductDto;
import model.dto.ProductDto;
import model.service.ProductSeriveImpl;
import model.service.ProductService;

import java.util.List;

public class ProductController {
    private final ProductService productService = new ProductSeriveImpl();

    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

   public List<ProductDto> addNewProduct(CreateProductDto createProductDto){
        return productService.addNewProduct(createProductDto);
   }
   public List<ProductDto> deleteProduct(Integer id){
        return productService.deleteProduct(id);
   }
}
