package com.douzone.douzonejdbc.exception.product;

public class ProductNotEnoughException extends Exception {

    private static final String ERROR = "출고하고자 하는 제품의 재고수량이 부족합니다.";

    public ProductNotEnoughException() {
        super(ERROR);
    }
}
