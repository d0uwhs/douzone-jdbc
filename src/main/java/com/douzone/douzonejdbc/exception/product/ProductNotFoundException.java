package com.douzone.douzonejdbc.exception.product;

import com.douzone.douzonejdbc.exception.NotFoundException;

public class ProductNotFoundException extends NotFoundException {
    private static final String ERROR = "상품을 찾을 수 없습니다.";

    public ProductNotFoundException() {
        super(ERROR);
    }
}
