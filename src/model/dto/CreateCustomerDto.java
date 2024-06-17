package model.dto;

import lombok.Builder;

import java.sql.Date;

@Builder
public record CreateCustomerDto(
        Integer id,
        String name,
        String email,
        String password,
        Boolean isDeleted,
        Date createDate
) {
}
