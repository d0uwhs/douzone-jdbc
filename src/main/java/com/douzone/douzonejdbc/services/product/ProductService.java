package com.douzone.douzonejdbc.services.product;

import com.douzone.douzonejdbc.dto.ProductDto;
import com.douzone.douzonejdbc.exception.product.ProductNotEnoughException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ProductService {

    List<ProductDto> getAllProducts();

    List<ProductDto> getImportProducts();

    List<ProductDto> getProducts(ProductDto productDto);

    List<ProductDto> getExportProducts();

    Integer importProduct(ProductDto productDto);

    Integer exportProducts(ProductDto productDto) throws SQLException, ProductNotEnoughException;


}
