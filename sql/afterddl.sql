
ALTER TABLE 결제 ADD PRIMARY KEY(결제번호);

ALTER TABLE 결제 ADD CONSTRAINT 결제_fk_01 foreign KEY(주문번호) references 주문 (주문번호);
ALTER TABLE 결제 ADD CONSTRAINT 결제_fk_02 foreign KEY(결제방식코드) references 결제방식 (결제방식코드);

ALTER TABLE 결제 LOGGING;
ALTER TABLE 주문 LOGGING;
ALTER TABLE 고객 LOGGING;
ALTER TABLE 상품 LOGGING;
