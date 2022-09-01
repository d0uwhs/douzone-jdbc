alter session set current_schema = MINJUNKIM;

DROP TABLE PRODUCT_STOCK;
DROP TABLE PRODUCT_IO;
DROP SEQUENCE IO_NUM_AUTO_INCREMENTS_SEQ;

CREATE TABLE PRODUCT_STOCK
(
    PRODUCT_ID  VARCHAR2(30) PRIMARY KEY,
    P_NAME      VARCHAR2(30) NOT NULL,
    PRICE       NUMBER(10)   NOT NULL,
    DESCRIPTION VARCHAR2(50),
    STOCK       NUMBER DEFAULT 0
);

--상품정보 insert
INSERT INTO PRODUCT_STOCK
VALUES ('nb_ss7', '삼성노트북', 1570000, '시리즈 7', DEFAULT);
INSERT INTO PRODUCT_STOCK
VALUES ('nb_macbook_air', '맥북에어', 1200000, 'xcode 4', DEFAULT);
INSERT INTO PRODUCT_STOCK
VALUES ('pc_ibm', 'ibmPC', 750000, 'windows 8', DEFAULT);
commit;

-- PRODUCT 테이블에 상품 삭제 시 PRODUCT_IO 테이블에도 해당 상품 관련된 데이터들 삭제 되도록 외래키 제약조건 옵션 부여
-- DELETE CASCASE

-- IO_DATE 컬럼의 기본값 SYSDATE, STATUS 컬럼 값으로는 ‘입고’, ‘출고’만 INSERT 될 수 있도 록 제약조건 부여할 것
-- CHECK (STATUS IN ())

CREATE TABLE PRODUCT_IO
(
    IO_NUM     VARCHAR2(20) PRIMARY KEY,
    PRODUCT_ID VARCHAR2(20)         NOT NULL,
    IO_DATE    DATE DEFAULT SYSDATE NOT NULL,
    AMOUNT     NUMBER               NOT NULL,
    STATUS     VARCHAR2(10)         NOT NULL CHECK ( STATUS IN ('입고', '출고') ),

    FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT_STOCK (PRODUCT_ID) ON DELETE CASCADE
);

-- PRODUCT_IO 테이블에 데이터 INSERT시 IO_NUM을 위한 SEQUENCE 생성 후 작업할 것
CREATE SEQUENCE MINJUNKIM.IO_NUM_AUTO_INCREMENTS_SEQ
    START WITH 1
    INCREMENT BY 1;

CREATE OR REPLACE TRIGGER TRG_01
    AFTER INSERT
    ON PRODUCT_IO
    FOR EACH ROW
BEGIN
    IF :NEW.STATUS = '입고'
    THEN
        UPDATE PRODUCT_STOCK
        SET STOCK = STOCK + :NEW.AMOUNT
        WHERE PRODUCT_ID = :NEW.PRODUCT_ID;
    end if;
    IF :NEW.STATUS = '출고'
    THEN
        UPDATE PRODUCT_STOCK
        SET STOCK = STOCK - :NEW.AMOUNT
        WHERE PRODUCT_ID = :NEW.PRODUCT_ID;
    end if;
end;



INSERT INTO PRODUCT_IO (IO_NUM, PRODUCT_ID, AMOUNT, STATUS)
VALUES (IO_NUM_AUTO_INCREMENTS_SEQ.nextval, 'nb_ss7', 30, '입고');
INSERT INTO PRODUCT_IO (IO_NUM, PRODUCT_ID, AMOUNT, STATUS)
VALUES (IO_NUM_AUTO_INCREMENTS_SEQ.nextval, 'nb_ss7', 3, '출고');
INSERT INTO PRODUCT_IO (IO_NUM, PRODUCT_ID, AMOUNT, STATUS)
VALUES (IO_NUM_AUTO_INCREMENTS_SEQ.nextval, 'pc_ibm', 10, '입고');
commit;


-- PRODUCT_IO

-- 상품 전체 조회 JOIN PRODUCT STOCK
SELECT *
FROM PRODUCT_IO
         JOIN PRODUCT_STOCK PS on PS.PRODUCT_ID = PRODUCT_IO.PRODUCT_ID;

-- 입출고 리스트
SELECT IO_NUM, PS.PRODUCT_ID, P_NAME, IO_DATE, AMOUNT, STATUS
FROM PRODUCT_IO
         JOIN PRODUCT_STOCK PS on PS.PRODUCT_ID = PRODUCT_IO.PRODUCT_ID;

-- 상품 입고내역 조회
SELECT *
FROM PRODUCT_IO
         JOIN PRODUCT_STOCK PS on PS.PRODUCT_ID = PRODUCT_IO.PRODUCT_ID
WHERE STATUS = '입고';

-- 상품 출고내역 조회
SELECT *
FROM PRODUCT_IO
         JOIN PRODUCT_STOCK PS on PS.PRODUCT_ID = PRODUCT_IO.PRODUCT_ID
WHERE STATUS = '출고';

-- 입출고 리스트
SELECT IO_NUM, PS.PRODUCT_ID, P_NAME, IO_DATE, AMOUNT, STATUS
FROM PRODUCT_IO
         JOIN PRODUCT_STOCK PS on PS.PRODUCT_ID = PRODUCT_IO.PRODUCT_ID
WHERE STATUS = '입고';

-- 상품 입고
INSERT INTO PRODUCT_IO (IO_NUM, PRODUCT_ID, AMOUNT, STATUS)
VALUES (IO_NUM_AUTO_INCREMENTS_SEQ.nextval, ?, ?, '입고');

-- 상품 출고
INSERT INTO PRODUCT_IO (IO_NUM, PRODUCT_ID, AMOUNT, STATUS)
VALUES (IO_NUM_AUTO_INCREMENTS_SEQ.nextval, ?, ?, '출고');


-- PRODUCT_STOCK

-- 상품 재고 조회 WHERE PRODUCT_ID
SELECT *
FROM PRODUCT_STOCK PS
WHERE PS.PRODUCT_ID = ?;