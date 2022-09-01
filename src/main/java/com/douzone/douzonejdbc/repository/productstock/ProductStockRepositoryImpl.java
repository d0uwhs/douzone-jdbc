package com.douzone.douzonejdbc.repository.productstock;

import com.douzone.douzonejdbc.dto.ProductDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductStockRepositoryImpl implements ProductStockRepository {

    private static final ProductStockRepositoryImpl instance = new ProductStockRepositoryImpl();

    private ProductStockRepositoryImpl() {
    }

    public static ProductStockRepositoryImpl getInstance() {
        return instance;
    }

    @Override
    public List<ProductDto> findSelectedProducts(Connection connection, ProductDto productDto) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM PRODUCT_STOCK PS WHERE PS.PRODUCT_ID = ?"
        )) {
            preparedStatement.setString(1, productDto.getProductId());
            List<ProductDto> productDtoList = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ProductDto findSelectedProducts = new ProductDto();
                findSelectedProducts.setProductId(resultSet.getString("PRODUCT_ID"));
                findSelectedProducts.setpName(resultSet.getString("P_NAME"));
                findSelectedProducts.setPrice(resultSet.getLong("PRICE"));
                findSelectedProducts.setDescription(resultSet.getString("DESCRIPTION"));
                findSelectedProducts.setStock(resultSet.getLong("STOCK"));
                productDtoList.add(findSelectedProducts);
            }
            return productDtoList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<ProductDto> findAllRegisteredProducts(Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM PRODUCT_STOCK"
        )) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<ProductDto> productDtoList = new ArrayList<>();
            while (resultSet.next()) {
                ProductDto findSelectedProducts = new ProductDto();
                findSelectedProducts.setProductId(resultSet.getString("PRODUCT_ID"));
                findSelectedProducts.setpName(resultSet.getString("P_NAME"));
                findSelectedProducts.setPrice(resultSet.getLong("PRICE"));
                findSelectedProducts.setDescription(resultSet.getString("DESCRIPTION"));
                findSelectedProducts.setStock(resultSet.getLong("STOCK"));
                productDtoList.add(findSelectedProducts);
            }
            return productDtoList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer registerProduct(Connection connection, ProductDto productDto) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO PRODUCT_STOCK VALUES (?, ?, ?, ?, DEFAULT)"
        )) {
            preparedStatement.setString(1, productDto.getProductId());
            preparedStatement.setString(2, productDto.getpName());
            preparedStatement.setLong(3, productDto.getPrice());
            preparedStatement.setString(4, productDto.getDescription());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer editProduct(Connection connection, ProductDto productDto) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE PRODUCT_STOCK SET P_NAME = ?, PRICE = ?, DESCRIPTION = ? WHERE PRODUCT_ID = ?"
        )) {
            preparedStatement.setString(1, productDto.getpName());
            preparedStatement.setLong(2, productDto.getPrice());
            preparedStatement.setString(3, productDto.getDescription());
            preparedStatement.setString(4, productDto.getProductId());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer removeProduct(Connection connection, String productId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM PRODUCT_STOCK WHERE PRODUCT_ID = ?"
        )) {
            preparedStatement.setString(1, productId);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ProductDto> findByRegisteredProducts(Connection connection, String productId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM PRODUCT_STOCK WHERE PRODUCT_ID = ?"
        )) {
            preparedStatement.setString(1,productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<ProductDto> productDtoList = new ArrayList<>();
            while (resultSet.next()) {
                ProductDto findSelectedProducts = new ProductDto();
                findSelectedProducts.setProductId(resultSet.getString("PRODUCT_ID"));
                findSelectedProducts.setpName(resultSet.getString("P_NAME"));
                findSelectedProducts.setPrice(resultSet.getLong("PRICE"));
                findSelectedProducts.setDescription(resultSet.getString("DESCRIPTION"));
                findSelectedProducts.setStock(resultSet.getLong("STOCK"));
                productDtoList.add(findSelectedProducts);
            }
            return productDtoList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
