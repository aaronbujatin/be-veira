package com.aaronbujatin.beveira.service;

import com.aaronbujatin.beveira.dto.ProductDto;
import com.aaronbujatin.beveira.entity.Product;
import com.aaronbujatin.beveira.entity.ProductVariants;
import com.aaronbujatin.beveira.repository.ProductRepository;
import com.aaronbujatin.beveira.repository.ProductVariantsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ProductVariantsRepository productVariantsRepository;

    @Override
    public Product saveProduct(Product product) {
        productRepository.save(product);

        for(ProductVariants variants : product.getVariants()){
            variants.setProduct(product);
            productVariantsRepository.save(variants);
        }

        return product;
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(ProductDto productDto, Long id) {

        Product product = productRepository.findById(id).get();
        if(product != null) {
            product.setName(productDto.getName());
            product.setPrice(productDto.getPrice());
            product.setBrand(productDto.getBrand());
            product.setColor(productDto.getColor());
            product.setImageUrl(productDto.getImageUrl());
            product.setCatalog(productDto.getCatalog());
        }

        return product;
    }

    @Override
    public String deleteProductById(Long id) {
         productRepository.deleteById(id);
        return "Product id : " + id + " was successfully deleted";
    }

    public List<Product> getAllByCatalog(String name){
//        String[] keywords = name.split("\\s+");
//
//        List<Product> searchResults = new ArrayList<>();
//
//        for(String keyword : keywords){
//            List<Product> products = productRepository.findByCatalogContainingIgnoreCase(name);
//            searchResults.addAll(products);
//        }

        return productRepository.findByCatalogContainingIgnoreCase(name);
    }
}
