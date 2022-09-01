package com.douzone.douzonejdbc.services.product;

import com.douzone.douzonejdbc.dto.ProductDto;
import com.douzone.douzonejdbc.exception.product.ProductNotEnoughException;
import com.douzone.douzonejdbc.repository.productio.ProductIORepository;
import com.douzone.douzonejdbc.repository.productio.ProductIORepositoryImpl;
import com.douzone.douzonejdbc.repository.productstock.ProductStockRepository;
import com.douzone.douzonejdbc.repository.productstock.ProductStockRepositoryImpl;

import java.sql.Connection;
import java.util.List;

import static com.douzone.douzonejdbc.common.Connector.*;


public class DefaultProductService implements ProductService {
    private final ProductIORepository productIORepository = ProductIORepositoryImpl.getInstance();
    private final ProductStockRepository productStockRepository = ProductStockRepositoryImpl.getInstance();

    private static final DefaultProductService instance = new DefaultProductService();

    private DefaultProductService() {

    }

    public static DefaultProductService getInstance() {
        return instance;
    }


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
    public Integer exportProducts(ProductDto productDto) throws ProductNotEnoughException {
        Connection connection = getConnection();
        List<ProductDto> getProducts = getProducts(productDto);
        Long findSelectedProductsAmount = getProducts.get(0).getStock();
        Long productDtoAmount = productDto.getAmount();
        Integer result = 0;

        /**
         * 재고보다 많은 경우
         */
        if (findSelectedProductsAmount - productDtoAmount > 0) {
            result = productIORepository.exportProduct(connection, productDto);
            return transaction(connection, result);
        }

        /**
         * 재고보다 없는 경우
         */
        if (findSelectedProductsAmount - productDtoAmount <= 0) {
            result = 0;
            transaction(connection, result);
            throw new ProductNotEnoughException();
        }
        return 0;
    }

    // TODO
    public static Integer transaction(Connection connection, Integer result) {
        if (result > 0) {
            commit(connection);
        }
        if (!(result > 0)) {
            rollback(connection);
        }
        return result;
    }
}
