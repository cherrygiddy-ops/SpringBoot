package com.morrisco.net.store.onlineStoreSystem.mappers;

import com.morrisco.net.store.onlineStoreSystem.dtos.ProductDto;
import com.morrisco.net.store.onlineStoreSystem.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "categoryId", source = "category.id") //customizing Mapping
    ProductDto toProductDto (Product product);

    Product toProductEntity(ProductDto productDto);

    @Mapping(target = "id", ignore = true)
    void updateUser(ProductDto request, @MappingTarget Product product);
}
