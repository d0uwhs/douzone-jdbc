package com.douzone.douzonejdbc.repository.product;

import com.douzone.douzonejdbc.dto.ProductDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {
    @Override
    public List<ProductDto> findAllByProduct(Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT IO_NUM, PS.PRODUCT_ID, P_NAME, IO_DATE, AMOUNT, STATUS FROM PRODUCT_IO JOIN PRODUCT_STOCK PS on PS.PRODUCT_ID = PRODUCT_IO.PRODUCT_ID"
        )) {
            List<ProductDto> productDtoList = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ProductDto productDto = new ProductDto();
                productDto.setIoNum(resultSet.getString("IO_NUM"));
                productDto.setProductId(resultSet.getString("PRODUCT_ID"));
                productDto.setpName(resultSet.getString("P_NAME"));
                productDto.setIoDate(resultSet.getDate("IO_DATE"));
                productDto.setAmount(resultSet.getLong("AMOUNT"));
                productDto.setStatus(resultSet.getString("STATUS"));
                productDtoList.add(productDto);
            }
            return productDtoList;
        } catch (SQLException e) {
            // TODO : Exception Throw?
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ProductDto> findImportProduct(Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT IO_NUM, PS.PRODUCT_ID, P_NAME, IO_DATE, AMOUNT, STATUS FROM PRODUCT_IO JOIN PRODUCT_STOCK PS on PS.PRODUCT_ID = PRODUCT_IO.PRODUCT_ID WHERE STATUS = '입고'"
        )) {
            List<ProductDto> productDtoList = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ProductDto productDto = new ProductDto();
                productDto.setIoNum(resultSet.getString("IO_NUM"));
                productDto.setProductId(resultSet.getString("PRODUCT_ID"));
                productDto.setpName(resultSet.getString("P_NAME"));
                productDto.setIoDate(resultSet.getDate("IO_DATE"));
                productDto.setAmount(resultSet.getLong("AMOUNT"));
                productDto.setStatus(resultSet.getString("STATUS"));
                productDtoList.add(productDto);
            }
            return productDtoList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<ProductDto> findExportProduct(Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT IO_NUM, PS.PRODUCT_ID, P_NAME, IO_DATE, AMOUNT, STATUS FROM PRODUCT_IO JOIN PRODUCT_STOCK PS on PS.PRODUCT_ID = PRODUCT_IO.PRODUCT_ID WHERE STATUS = '출고'"
        )) {
            List<ProductDto> productDtoList = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ProductDto productDto = new ProductDto();
                productDto.setIoNum(resultSet.getString("IO_NUM"));
                productDto.setProductId(resultSet.getString("PRODUCT_ID"));
                productDto.setpName(resultSet.getString("P_NAME"));
                productDto.setIoDate(resultSet.getDate("IO_DATE"));
                productDto.setAmount(resultSet.getLong("AMOUNT"));
                productDto.setStatus(resultSet.getString("STATUS"));
                productDtoList.add(productDto);
            }
            return productDtoList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer importProduct(Connection connection) {
        return null;
    }

    @Override
    public Integer exportProduct(Connection connection) {
        return null;
    }
}
