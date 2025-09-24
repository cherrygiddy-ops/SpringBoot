package com.morrisco.net.store.onlineStoreSystem.controllers;

import com.morrisco.net.store.onlineStoreSystem.dtos.ProductDto;
import com.morrisco.net.store.onlineStoreSystem.dtos.ProductSummaryUsingClass;
import com.morrisco.net.store.onlineStoreSystem.entities.Category;
import com.morrisco.net.store.onlineStoreSystem.mappers.ProductMapper;
import com.morrisco.net.store.onlineStoreSystem.repository.CategoryRepository;
import com.morrisco.net.store.onlineStoreSystem.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Getter
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductMapper mapper;
    private final CategoryRepository categoryRepository;

    @GetMapping()
    public Iterable<ProductDto> getProducts(@RequestHeader(name = "x-auth-token",required = false) String authToken,  @RequestParam(required = false,name = "categoryId") Byte categoryId){

        System.out.println(authToken);
        if (categoryId ==null)
               return productRepository.findAll().stream()
                .map(mapper::toProductDto)
                .toList();
           return productRepository.findByCategory(new Category(categoryId)).stream()
                   .map(mapper::toProductDto).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable  long id){
        var product = productRepository.findById(id).orElse(null);

        if (product==null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(mapper.toProductDto(product));
    }
}
