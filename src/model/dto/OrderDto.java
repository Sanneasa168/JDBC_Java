package model.dto;

import lombok.Builder;
import model.entity.Customer;
import model.entity.Order;
import model.entity.Product;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Builder
public record OrderDto(
        Integer id,
        String  order_name,
        String order_description,
        Customer cus_id,
        Date ordered_at

) {
    public Product[] getProductList() {
        List<Product> productList = new ArrayList<>();
        return productList.toArray(new Product[0]);
    }
}
