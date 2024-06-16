package model.dto;

import lombok.Builder;

import java.sql.Date;

@Builder
public record CreateProductDto(
        String product_name,
        String product_code,
        String product_description,
        Integer product_id,
        Boolean product_isDeleted,
        Date product_importedDate,
        Date product_expiredDate
) {

}
