package mapper;

import model.dto.ProductDto;
import model.entity.Product;

import java.util.List;

public class ProductMapper {
   public static ProductDto fromProductToProductDto(Product product) {
       if(product == null) return null;
       return   ProductDto.builder()
               .product_name(product.getProductName())
               .product_code(product.getProductCode())
               .product_description(product.getProductDescription())
               .build();
   }
}
