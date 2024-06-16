package model.dao;

import model.dto.CreateProductDto;
import model.entity.Product;

import java.util.List;

public interface ProductDao {
    List<Product> queryAllProducts();
    int addNewProduct(CreateProductDto product);
    Product  searchProduct(Integer id);
    int  updateProduct(Integer id);
    int  deleteProduct(Integer id);

}
