package com.douzone.douzonejdbc.services;

import com.douzone.douzonejdbc.dto.ProductDto;

import java.util.List;

public interface ProductService {

    List<ProductDto> getAllProducts();
    List<ProductDto> getImportProducts();
    List<ProductDto> getExportProducts();


}