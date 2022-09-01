package com.douzone.douzonejdbc.views;

import com.douzone.douzonejdbc.controller.product.ProductController;
import com.douzone.douzonejdbc.dto.ProductDto;
import com.douzone.douzonejdbc.exception.product.ProductNotEnoughException;

import java.sql.SQLException;
import java.util.List;

import static com.douzone.douzonejdbc.JDBCApplication.scanner;

public class IOProductMenu {
    private final ProductController productController = ProductController.getInstance();

    public void mainMenu() {
        while (true) {
            System.out.print("=== 입출고 메뉴 ===\n" +
                    "1. 전체 입출고 내역 조회\n" +
                    "2. 입고 내역 조회\n" +
                    "3. 출고 내역 조회\n" +
                    "4. 상품 입고\n" +
                    "5. 상품 출고\n" +
                    "9. 메인 메뉴로 돌아가기\n" +
                    "번호 선택 : ");
            String selectMenu = scanner.nextLine();

            // TODO : 보호절

            if (selectMenu.equals("1")) {
                getAllProducts();
            }
            if (selectMenu.equals("2")) {
                getImportProducts();
            }
            if (selectMenu.equals("3")) {
                getExportProducts();
            }
            if (selectMenu.equals("4")) {
                importProducts();
            }
            if (selectMenu.equals("5")) {
                exportProducts();
            }
            if (selectMenu.equals("9")) {
                break;
            }
        }
    }

    private void getAllProducts() {
        List<ProductDto> productDtoList = productController.getAllProducts();
        System.out.println("================== 입출고 리스트 ==================");
        for (ProductDto productDto : productDtoList) {
            System.out.println(productDto.toStringForIOList());
        }
        System.out.println("");
    }

    private void getImportProducts() {
        List<ProductDto> productDtoList = productController.getImportProducts();
        System.out.println("================== 입고 리스트 ==================");
        for (ProductDto productDto : productDtoList) {
            System.out.println(productDto.toStringForIOList());
        }
        System.out.println("");
    }

    private void getExportProducts() {
        List<ProductDto> productDtoList = productController.getExportProducts();
        System.out.println("================== 출고 리스트 ==================");
        for (ProductDto productDto : productDtoList) {
            System.out.println(productDto.toStringForIOList());
        }
        System.out.println("");
    }

    private void importProducts() {
        System.out.print("상품 아이디 : ");
        String productId = scanner.next();
        System.out.print("\n입고 수량 : ");
        Long amount = scanner.nextLong();
        ProductDto productDto = new ProductDto();
        productDto.setProductId(productId);
        productDto.setAmount(amount);
        Integer result = productController.importProducts(productDto);

        if (result > 0) {
            System.out.println("서비스 요청 성공 : 성공적으로 입고하였습니다.");
        }

        if (result.equals(0)) {
            System.out.println("서비스 요청 성공 : 성공적으로 입고하였습니다.");
        }
    }

    private void exportProducts() {
        System.out.print("상품 아이디 : ");
        String productId = scanner.next();
        System.out.print("\n출고 수량 : ");
        Long amount = scanner.nextLong();
        ProductDto productDto = new ProductDto();
        productDto.setProductId(productId);
        productDto.setAmount(amount);
        Integer result = null;
        try {
            result = productController.exportProducts(productDto);
            if (result > 0) {
                System.out.println("서비스 요청 성공 : 성공적으로 출고하였습니다.");
            }
        } catch (ProductNotEnoughException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
