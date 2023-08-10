package com.aaronbujatin.beveira.service;

import com.aaronbujatin.beveira.repository.ProductVariantsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductVariantsService {

    private final ProductVariantsRepository productVariantsRepository;



}
