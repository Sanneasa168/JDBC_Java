package model.dto;

import lombok.Builder;
import model.entity.Customer;

import java.sql.Date;
@Builder
public record CreateOrderDto(
        Integer id,
        String  order_name,
        String order_description,
        Customer cus_id,
        Date ordered_at
) {
}
