# 실행순서


# 1. firstddl.sql 실행

sql 폴더의 firstddl.sql을 오라클에 실행시킨다.

# 2. JAR 실행

JAR 폴더의 oracle_db_test_set.jar 을 아래의 명령어로 실행시킨다.
java -jar oracle_db_test_set.jar

입력.TXT는 아래와 같은 10개의 줄로 구성되어 있다.

    127.0.0.1
    1614
    oracle2
    orcl2
    orcl2
    15000
    1000000
    60000000
    55000000
    100000

IP PORT SID ID PASSWORD  5개와
상품 , 고객, 주문, 결제 테이블 크기를 결정하는 5개 줄로 구성되어있다.
각자의 사양과 원하는 크기에 맞게 다시 구성하고 CTRL+C 로 복사하고
JAR실행시 입력받는 칸에 SHIFT+INSERT로 입력한 뒤 엔터를 누르면 테이블 입력이 시작된다.
# 3. AFTERDDL.SQL 실행

sql 폴더의 afterddl.sql을 실행시킨다.

# 4. 주의사항
1)제 컴퓨터에서는 약 1.5억건의 insert를 실행하다보면 jvm이 메모리 문제로 실행 중단됩니다. 
그렇기에 적당한 크기의 사이즈를 insert하는게 좋습니다.

2)결제테이블은 주문번호를 1:1로 맞추기 위하여 작업을 합니다.
cpu를 상당히 잡아먹고 시간도 오래걸리기에 결제테이블이 필요없다고 생각하시면
0으로 사이즈를 맞춰주셔도 됩니다.
