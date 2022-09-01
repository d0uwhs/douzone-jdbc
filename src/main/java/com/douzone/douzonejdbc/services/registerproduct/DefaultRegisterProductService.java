package com.douzone.douzonejdbc.services.registerproduct;

import com.douzone.douzonejdbc.dto.ProductDto;
import com.douzone.douzonejdbc.exception.product.ProductNotFoundException;
import com.douzone.douzonejdbc.repository.productio.ProductIORepository;
import com.douzone.douzonejdbc.repository.productio.ProductIORepositoryImpl;
import com.douzone.douzonejdbc.repository.productstock.ProductStockRepository;
import com.douzone.douzonejdbc.repository.productstock.ProductStockRepositoryImpl;

import java.sql.Connection;
import java.util.List;

import static com.douzone.douzonejdbc.common.Connector.getConnection;
import static com.douzone.douzonejdbc.services.product.DefaultProductService.transaction;

public class DefaultRegisterProductService implements RegisterProductService {

    private final ProductIORepository productIORepository = ProductIORepositoryImpl.getInstance();

    private final ProductStockRepository productStockRepository = ProductStockRepositoryImpl.getInstance();


    private static final DefaultRegisterProductService instance = new DefaultRegisterProductService();

    private DefaultRegisterProductService() {
    }

    public static DefaultRegisterProductService getInstance(){
        return instance;
    }




    @Override
    public List<ProductDto> getAllRegisteredProducts() {
        Connection connection = getConnection();
        return productStockRepository.findAllRegisteredProducts(connection);
    }

    @Override
    public List<ProductDto> findByRegisteredProducts(String productId) throws ProductNotFoundException {
        Connection connection = getConnection();
        List<ProductDto> productDtoList = productStockRepository.findByRegisteredProducts(connection,productId);
        if (productDtoList.isEmpty()){
            throw new ProductNotFoundException();
        }
        return productDtoList;
    }

    @Override
    public Integer registerProduct(ProductDto productDto) {
        Connection connection = getConnection();
        Integer result =  productStockRepository.registerProduct(connection, productDto);
        return transaction(connection,result);
    }

    @Override
    public Integer removeProduct(ProductDto productDto) {
        return null;
    }

    @Override
    public Integer editProduct(ProductDto productDto) {
        return null;
    }


}
