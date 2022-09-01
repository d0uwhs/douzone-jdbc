package com.douzone.douzonejdbc.dto;

import java.sql.Date;

/**
 * Join 확장 Dto
 */
public class ProductDto {
    private String ioNum;
    private String productId;
    private String pName;
    private Date ioDate;
    private Long amount;
    private String status;
    private String description;
    private Long stock;
    private Long price;

    public ProductDto() {
    }

    public ProductDto(String ioNum, String productId, String pName, Date ioDate, Long amount, String status, String description, Long stock, Long price) {
        this.ioNum = ioNum;
        this.productId = productId;
        this.pName = pName;
        this.ioDate = ioDate;
        this.amount = amount;
        this.status = status;
        this.description = description;
        this.stock = stock;
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "ioNum='" + ioNum + '\'' +
                ", productId='" + productId + '\'' +
                ", pName='" + pName + '\'' +
                ", ioDate=" + ioDate +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", stock=" + stock +
                ", price=" + price +
                '}';
    }

    public String getIoNum() {
        return ioNum;
    }

    public void setIoNum(String ioNum) {
        this.ioNum = ioNum;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public Date getIoDate() {
        return ioDate;
    }

    public void setIoDate(Date ioDate) {
        this.ioDate = ioDate;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String toStringForIOList() {
        return ioNum + "  " + productId + "  " + pName + "  " + ioDate + "  " + amount + "  " + status;
    }

    public String toStringForProductList() {
        return productId + "  " + pName + "  " + price + "  " + description + "  " + stock;
    }
}
