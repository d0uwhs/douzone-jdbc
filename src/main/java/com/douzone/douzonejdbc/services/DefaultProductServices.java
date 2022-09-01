package com.douzone.douzonejdbc.services;

import com.douzone.douzonejdbc.dto.ProductDto;
import com.douzone.douzonejdbc.repository.productio.ProductIORepository;
import com.douzone.douzonejdbc.repository.productio.ProductIORepositoryImpl;
import com.douzone.douzonejdbc.repository.productstock.ProductStockRepository;
import com.douzone.douzonejdbc.repository.productstock.ProductStockRepositoryImpl;

import java.sql.Connection;
import java.util.List;

import static com.douzone.douzonejdbc.common.Connector.*;


public class DefaultProductServices implements ProductService {

    private static final DefaultProductServices instance = new DefaultProductServices();

    private DefaultProductServices() {

    }

    public static DefaultProductServices getInstance() {
        return instance;
    }


    private final ProductIORepository productIORepository = ProductIORepositoryImpl.getInstance();
    private final ProductStockRepository productStockRepository = ProductStockRepositoryImpl.getInstance();

//    public DefaultProductServices(ProductIORepository productIORepository, ProductStockRepository productStockRepository) {
//        this.productIORepository = productIORepository;
//        this.productStockRepository = productStockRepository;
//    }

    @Override
    public List<ProductDto> getAllProducts() {
        Connection connection = getConnection();
        return productIORepository.findAllByProduct(connection);
    }

    @Override
    public List<ProductDto> getImportProducts() {
        Connection connection = getConnection();
        return productIORepository.findImportProduct(connection);
    }

    @Override
    public List<ProductDto> getExportProducts() {
        Connection connection = getConnection();
        return productIORepository.findExportProduct(connection);
    }

    @Override
    public List<ProductDto> getProducts(ProductDto productDto) {
        Connection connection = getConnection();
        return productStockRepository.findSelectedProducts(connection, productDto);
    }

    @Override
    public Integer importProduct(ProductDto productDto) {
        Connection connection = getConnection();
        Integer result = productIORepository.importProduct(connection, productDto);
        return transaction(connection, result);
    }

    @Override
    public Integer exportProducts(ProductDto productDto) {
        Connection connection = getConnection();
        Integer result = productIORepository.exportProduct(connection, productDto);
        return transaction(connection, result);
    }

    private static Integer transaction(Connection connection, Integer result) {
        if (result > 0) {
            commit(connection);
        }
        if (!(result > 0)) {
            rollback(connection);
        }
        return result;
    }
}
