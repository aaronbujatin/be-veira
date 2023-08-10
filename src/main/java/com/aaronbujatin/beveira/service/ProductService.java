package com.aaronbujatin.beveira.service;

import com.aaronbujatin.beveira.dto.ProductDto;
import com.aaronbujatin.beveira.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {

    Product saveProduct(Product product);

    Product getProductById(Long id);

    List<Product> getAllProducts();

    Product updateProduct(ProductDto productDto, Long id);

    String deleteProductById(Long id);

    List<Product> getAllByCatalog(String name);

}
