package model.dto;

import lombok.Builder;

import java.util.Date;

@Builder
public record ProductDto(
        String product_name,
        String product_code,
        String product_description,
        Integer product_id


) {
}
