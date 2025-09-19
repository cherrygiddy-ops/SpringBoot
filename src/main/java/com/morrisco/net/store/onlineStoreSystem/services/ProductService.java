package com.morrisco.net.store.onlineStoreSystem.services;

import com.morrisco.net.store.onlineStoreSystem.entities.Category;
import com.morrisco.net.store.onlineStoreSystem.entities.Product;
import com.morrisco.net.store.onlineStoreSystem.repository.CategoryRepository;
import com.morrisco.net.store.onlineStoreSystem.repository.ProductRepository;
import com.morrisco.net.store.onlineStoreSystem.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class ProductService {
    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    UserRepository userRepository;
    public void showRelatedEntities(){
        var product =Product.builder()
                .price(BigDecimal.valueOf(10))
                .name("A").build();
        var product1 =productRepository.findById(1L).orElseThrow();
        System.out.println(product1);
    }
    @Transactional
    public void persistRelatedEntity(){
//        var categ= Category.builder().name("beverages").build();
//        categoryRepository.save(categ);
        var category = categoryRepository.findById((byte) 3).orElseThrow();
        var product =Product.builder()
                .price(BigDecimal.valueOf(10))
                .name("A")
                .category(category).build();
        productRepository.save(product);
    }
    @Transactional
    public void addProductsToUsersWishList(){
        var user = userRepository.findById(5).orElseThrow();
         productRepository.findAll().forEach(user::addProduct);

        userRepository.save(user);
    }
    public void deleteProduct(){
        productRepository.deleteById(4L);
    }


    @Transactional
    public void updateProduct(int a, byte b){
         productRepository.updatePriceByCategory(BigDecimal.valueOf(a), b);
    }

    public void fetchProducts(byte categoryId){
        System.out.println(productRepository.findByCategory(new Category(categoryId)));
    }
}

