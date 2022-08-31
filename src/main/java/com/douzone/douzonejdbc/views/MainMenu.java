package com.douzone.douzonejdbc.views;

import com.douzone.douzonejdbc.controller.ProductController;
import com.douzone.douzonejdbc.dto.ProductDto;

import java.util.List;

import static com.douzone.douzonejdbc.JDBCApplication.scanner;

public class MainMenu {
    private ProductController productController = new ProductController();

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


            if (selectMenu.equals("9")) {
                break;
            }
        }
    }

    public void getAllProducts() {
        List<ProductDto> productDtoList = productController.getAllProducts();
        System.out.println("================== 입출고 리스트 ==================");
        for (ProductDto productDto : productDtoList) {
            System.out.println(productDto.toStringForGetAllProducts());
        }
        System.out.println("");
    }

    public void getImportProducts() {
        List<ProductDto> productDtoList = productController.getImportProducts();
        System.out.println("================== 입고 리스트 ==================");
        for (ProductDto productDto : productDtoList) {
            System.out.println(productDto.toStringForGetAllProducts());
        }
        System.out.println("");
    }

    public void getExportProducts() {
        List<ProductDto> productDtoList = productController.getExportProducts();
        System.out.println("================== 출고 리스트 ==================");
        for (ProductDto productDto : productDtoList) {
            System.out.println(productDto.toStringForGetAllProducts());
        }
        System.out.println("");
    }
}
