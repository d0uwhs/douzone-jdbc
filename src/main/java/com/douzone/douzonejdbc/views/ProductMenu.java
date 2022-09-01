package com.douzone.douzonejdbc.views;


import com.douzone.douzonejdbc.controller.registerproduct.RegisterProductController;
import com.douzone.douzonejdbc.dto.ProductDto;
import com.douzone.douzonejdbc.exception.product.ProductNotFoundException;

import java.util.List;

import static com.douzone.douzonejdbc.JDBCApplication.scanner;

public class ProductMenu {
    private final RegisterProductController registerProductController = RegisterProductController.getInstance();
    private final IOProductMenu IOProductMenu = new IOProductMenu();

    public void mainMenu() {
        while (true) {
            System.out.print("=== 상품 관리 프로그램 ===\n" +
                    "1. 상품 전체 조회\n" +
                    "2. 상품 추가\n" +
                    "3. 상품 수정\n" +
                    "4. 상품 삭제\n" +
                    "5. 상품 검색\n" +
                    "6. 상품 입출고 메뉴\n" +
                    "9. 메인 메뉴로 돌아가기\n" +
                    "번호 선택 : ");
            String selectMenu = scanner.nextLine();

            if (selectMenu.equals("1")) {
                getAllRegisteredProducts();
            }
            if (selectMenu.equals("2")) {
                registerProduct();
            }
            if (selectMenu.equals("3")) {
                editProduct();
            }
            if (selectMenu.equals("4")) {
                removeProduct();
            }
            if (selectMenu.equals("5")) {
                findByRegisteredProducts();
            }
            if (selectMenu.equals("6")) {
                IOProductMenu.mainMenu();
            }
            if (selectMenu.equals("9")) {
                break;
            }
        }
    }

    private void findByRegisteredProducts() {
        System.out.print("상품 아이디 : ");
        String productId = scanner.nextLine();
        try {
            List<ProductDto> productDtoList = registerProductController.findByRegisteredProducts(productId);
            for (ProductDto productDto : productDtoList) {
                System.out.println(productDto.toStringForProductList());
            }
            System.out.println("");
        } catch (ProductNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void removeProduct() {
        System.out.print("상품 아이디 : ");
        String productId = scanner.nextLine();
        Integer result = registerProductController.removeProduct(productId);

        if (result > 0) {
            System.out.println("서비스 요청 성공 : 성공적으로 삭제하였습니다.");
        }

        if (result.equals(0)) {
            System.out.println("삭제된 정보가 없습니다.");
        }
    }

    private void editProduct() {
        System.out.print("상품 아이디 : ");
        String productId = scanner.nextLine();

        System.out.print("\n상품명 : ");
        String pName = scanner.nextLine();

        System.out.print("\n가격 : ");
        String price = scanner.nextLine();

        System.out.print("\n부가설명 : ");
        String description = scanner.nextLine();

        ProductDto productDto = new ProductDto();
        productDto.setProductId(productId);
        productDto.setpName(pName);
        productDto.setPrice(Long.parseLong(price));
        productDto.setDescription(description);
        Integer result = registerProductController.editProduct(productDto);

        if (result > 0) {
            System.out.println("서비스 요청 성공 : 성공적으로 수정하였습니다.");
        }

        if (result.equals(0)) {
            System.out.println("수정된 정보가 없습니다.");
        }
    }

    /**
     * Scanner 입력값 오류
     * https://yangbox.tistory.com/53
     * 모든 값을 nextLine으로 받고, 형변환
     */
    private void registerProduct() {
        System.out.print("상품 아이디 : ");
        String productId = scanner.nextLine();

        System.out.print("\n상품명 : ");
        String pName = scanner.nextLine();

        System.out.print("\n가격 : ");
        String price = scanner.nextLine();

        System.out.print("\n부가설명 : ");
        String description = scanner.nextLine();

        ProductDto productDto = new ProductDto();
        productDto.setProductId(productId);
        productDto.setpName(pName);
        productDto.setPrice(Long.parseLong(price));
        productDto.setDescription(description);
        Integer result = registerProductController.registerProduct(productDto);
        if (result > 0) {
            System.out.println("서비스 요청 성공 : 성공적으로 등록하였습니다.");
        }
    }

    private void getAllRegisteredProducts() {
        List<ProductDto> productDtoList = registerProductController.getAllRegisteredProducts();
        System.out.println("================== 상품 리스트 ==================");
        for (ProductDto productDto : productDtoList) {
            System.out.println(productDto.toStringForProductList());
        }
        System.out.println("");

    }
}
