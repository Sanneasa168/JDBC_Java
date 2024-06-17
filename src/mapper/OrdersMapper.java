package mapper;

import model.dto.OrderDto;
import model.dto.ProductDto;
import model.entity.Order;
import model.entity.Product;

public class OrdersMapper {
    public static OrderDto formOderToOrderDto(Order order) {
        if (order == null) return null;
        return OrderDto.builder()
                .id(order.getId())
                .order_name(order.getOrderName())
                .order_description(order.getOrderDescription())
                .cus_id(order.getCustomer())
                .ordered_at(order.getOrderedAt())
                .build();
    }
}


//public static ProductDto fromProductToProductDto(Product product) {
//    if(product == null) return null;
//    return   ProductDto.builder()
//            .product_name(product.getProductName())
//            .product_code(product.getProductCode())
//            .product_description(product.getProductDescription())
//            .build();
//}
