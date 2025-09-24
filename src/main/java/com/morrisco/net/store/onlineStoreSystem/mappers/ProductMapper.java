package com.morrisco.net.store.onlineStoreSystem.mappers;

import com.morrisco.net.store.onlineStoreSystem.dtos.ProductDto;
import com.morrisco.net.store.onlineStoreSystem.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping( source = "category.id", target = "categoryId") //customizing Mapping
    ProductDto toProductDto (Product product);
}
