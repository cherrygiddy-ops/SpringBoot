package com.morrisco.net.store.onlineStoreSystem.controllers;

import com.morrisco.net.store.onlineStoreSystem.dtos.ProductDto;
import com.morrisco.net.store.onlineStoreSystem.dtos.ProductSummaryUsingClass;
import com.morrisco.net.store.onlineStoreSystem.dtos.UpdateUserRequest;
import com.morrisco.net.store.onlineStoreSystem.dtos.UserDto;
import com.morrisco.net.store.onlineStoreSystem.entities.Category;
import com.morrisco.net.store.onlineStoreSystem.mappers.ProductMapper;
import com.morrisco.net.store.onlineStoreSystem.repository.CategoryRepository;
import com.morrisco.net.store.onlineStoreSystem.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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

    @PostMapping
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto, UriComponentsBuilder uriBuilder){
        var category = categoryRepository.findById(productDto.getCategoryId()).orElse(null);
        if (category==null)
            return ResponseEntity.badRequest().build();
        System.out.println(category);
        var product = mapper.toProductEntity(productDto);
        product.setCategory(category);
        productRepository.save(product);

        var productDto1=mapper.toProductDto(product);

        var uri =uriBuilder.path("/products/{id}").buildAndExpand(productDto.getId()).toUri();
        return ResponseEntity.created(uri).body(productDto1);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto request, @PathVariable(name = "id") Long id){
        var category = categoryRepository.findById(request.getCategoryId()).orElse(null);
        if (category==null)
            return ResponseEntity.badRequest().build();
        var product =productRepository.findById(id).orElse(null);

        if (product == null)
            return ResponseEntity.notFound().build();

        mapper.updateUser(request,product);
        product.setCategory(category);
        productRepository.save(product);

        return ResponseEntity.ok(mapper.toProductDto(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable(name = "id") long id) {
        var product = productRepository.findById(id).orElse(null);

        if (product == null)
            return ResponseEntity.notFound().build();
        productRepository.delete(product);
     return ResponseEntity.noContent().build();
    }
    }
