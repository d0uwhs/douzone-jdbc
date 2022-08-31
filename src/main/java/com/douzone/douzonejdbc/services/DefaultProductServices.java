package com.douzone.douzonejdbc.services;

import com.douzone.douzonejdbc.dto.ProductDto;
import com.douzone.douzonejdbc.repository.product.ProductRepository;

import java.sql.Connection;
import java.util.List;

import static com.douzone.douzonejdbc.common.Connector.getConnection;


public class DefaultProductServices implements ProductService {
    private final ProductRepository productRepository;

    public DefaultProductServices(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        Connection connection = getConnection();
        return productRepository.findAllByProduct(connection);
    }

    @Override
    public List<ProductDto> getImportProducts() {
        Connection connection = getConnection();
        return productRepository.findImportProduct(connection);
    }

    @Override
    public List<ProductDto> getExportProducts() {
        Connection connection = getConnection();
        return productRepository.findExportProduct(connection);
    }
}
