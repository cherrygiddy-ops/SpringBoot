package com.morrisco.net.store.onlineStoreSystem.services;

import com.morrisco.net.store.onlineStoreSystem.entities.Category;
import com.morrisco.net.store.onlineStoreSystem.entities.Product;
import com.morrisco.net.store.onlineStoreSystem.repository.CategoryRepository;
import com.morrisco.net.store.onlineStoreSystem.repository.ProductRepository;
import com.morrisco.net.store.onlineStoreSystem.repository.UserRepository;
import com.morrisco.net.store.onlineStoreSystem.repository.specification.ProductSpec;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
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

    @Transactional
    public void fetchProductsByRange(int min, int max){
        System.out.println(productRepository.findProductsUsingStoredProcedure(BigDecimal.valueOf(min), BigDecimal.valueOf(max)));
    }

    public void fetchProductsByQueryingExample(){
        var product = new Product();
        product.setName("a");
        var matcher = ExampleMatcher.matching()
                //.withIncludeNullValues()
                //.withIgnorePaths()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        var example = Example.of(product,matcher);
        productRepository.findAll(example).forEach(System.out::println);

        //LIMITATIONS OF QUERYING BY EXAMPLE
        /**
         * No support for nested properties
         * No support for Matching collections/maps
         * Database specific support for matching Strings
         * Exact Matching for other types (e.g numbers/dates)
         */
    }


    public void fetchProductsByCriteria(){
        productRepository.findProductsByCriteria("A",BigDecimal.valueOf(6),BigDecimal.valueOf(15)).forEach(System.out::println);
    }

    public void fetchProductsBySpecification(String name,BigDecimal minPrice,BigDecimal maxPrice){
        @SuppressWarnings("removal") Specification<Product>specification = Specification.where(null);
        if (name != null){
            specification =specification.and(ProductSpec.hasName(name));
        }
        if (minPrice != null){
            specification =specification.and(ProductSpec.hasPriceGreaterThanOrEqualTo(minPrice));
        }
        if (maxPrice != null){
            specification =specification.and(ProductSpec.hasPriceLessThanOrEqualTo(maxPrice));
        }

        productRepository.findAll(specification).forEach(System.out::println);
    }

    public void fetchSortedProducts(){
      var sort = Sort.by("name").and(Sort.by("price").descending());

      productRepository.findAll(sort).forEach(System.out::println);
    }

    public void fetchPaginatedProducts(int pageNumber,int size){
        PageRequest pageRequest = PageRequest.of(pageNumber,size);
        Page<Product>page=productRepository.findAll(pageRequest);

        //getting products in each page
        var products = page.getContent();
        products.forEach(System.out::println);

        //getting total numbers of pages
        var totalPages =page.getTotalPages();
        var totalElements = page.getTotalElements();

        System.out.println(totalPages);
        System.out.println(totalElements);

    }
}

