TRUNCATE TABLE 결제;
TRUNCATE TABLE 주문;
TRUNCATE TABLE 결제방식;
TRUNCATE TABLE 고객;
TRUNCATE TABLE 상품;
DROP TABLE 결제;
DROP TABLE 주문;
DROP TABLE 결제방식;
DROP TABLE 고객;
DROP TABLE 상품;
CREATE TABLE 결제방식 
(
  결제방식코드 VARCHAR2(1) NOT NULL 
, 결제방식명 VARCHAR2(1) 
, CONSTRAINT 결제방식_PK PRIMARY KEY 
  (
    결제방식코드 
  )
  ENABLE 
);

CREATE TABLE 상품 
(
  상품번호 NUMBER NOT NULL 
, 상품명 VARCHAR2(5) 
, 가격 NUMBER 
, 공급업체코드 VARCHAR2(2) 
, CONSTRAINT 상품_PK PRIMARY KEY 
  (
    상품번호 
  )
  ENABLE 
);
CREATE TABLE 고객 
(
  고객번호 NUMBER NOT NULL 
, 성별 VARCHAR2(1) 
, 가입일자 DATE 
, 연락처 VARCHAR2(11) 
, 연령 NUMBER 
, 고객등급 VARCHAR2(1) 
, 고객명 VARCHAR2(5)
, CONSTRAINT 고객_PK PRIMARY KEY 
  (
    고객번호
  )
  ENABLE 
);
CREATE TABLE 주문 
(
  주문번호 NUMBER NOT NULL 
, 상품번호 NUMBER 
, 고객번호 NUMBER 
, 주문일자 DATE 
, 주문금액 NUMBER 
, 배송지 VARCHAR2(2) 
,CONSTRAINT 주문fk_code1 FOREIGN KEY(고객번호)
         REFERENCES 고객(고객번호) ON DELETE CASCADE,
CONSTRAINT 주문fk_code2 FOREIGN KEY(상품번호)
         REFERENCES 상품(상품번호) ON DELETE CASCADE,
 CONSTRAINT 주문_PK PRIMARY KEY 
  (
    주문번호 
  )
  ENABLE 
);

CREATE TABLE 결제 
(
  결제번호 NUMBER NOT NULL 
, 주문번호 NUMBER 
, 결제방식코드 VARCHAR2(1) 
, 결제금액 NUMBER 
, 결제일자 DATE 
, 주문일자 DATE 

);

ALTER TABLE 결제 NOLOGGING;
ALTER TABLE 주문 NOLOGGING;
ALTER TABLE 고객 NOLOGGING;
ALTER TABLE 상품 NOLOGGING;