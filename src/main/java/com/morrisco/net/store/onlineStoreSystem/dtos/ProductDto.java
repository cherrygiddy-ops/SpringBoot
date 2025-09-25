package com.morrisco.net.store.onlineStoreSystem.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.mapstruct.Mapper;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private Byte categoryId;
}
