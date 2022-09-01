package com.douzone.douzonejdbc.repository.productio;

import com.douzone.douzonejdbc.dto.ProductDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductIORepositoryImpl implements ProductIORepository {
    private static final ProductIORepositoryImpl instance = new ProductIORepositoryImpl();

    private ProductIORepositoryImpl() {
    }

    public static ProductIORepositoryImpl getInstance() {
        return instance;
    }

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
    public Integer importProduct(Connection connection, ProductDto productDto) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO PRODUCT_IO (IO_NUM, PRODUCT_ID, AMOUNT, STATUS) VALUES (IO_NUM_AUTO_INCREMENTS_SEQ.nextval,?,?,'입고')"
        )) {
            preparedStatement.setString(1, productDto.getProductId());
            preparedStatement.setLong(2, productDto.getAmount());
            Integer result = preparedStatement.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Integer exportProduct(Connection connection, ProductDto productDto) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO PRODUCT_IO (IO_NUM, PRODUCT_ID, AMOUNT, STATUS) VALUES (IO_NUM_AUTO_INCREMENTS_SEQ.nextval,?,?,'출고')"
        )) {
            preparedStatement.setString(1, productDto.getProductId());
            preparedStatement.setLong(2, productDto.getAmount());
            Integer result = preparedStatement.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
