- 외래키
    
    테이블간의 관계를 나타낼 때 사용하며, 다른 테이블의 기본키를 참조해 외래키로 사용한다. 즉, 한 테이블의 외래키는 연결되어있는 다른 테이블의 기본키 중 하나이다. 
    
- 기본키
    
    PK 등으로 불린다. 후보키들 중 메인으로 선정되고 유일성과 최소성을 가진다. 하나의 테이블에는 반드시 하나의 기본키만 존재한다. 
    
- ER 다이어그램
    
    An Entity Relationship Diagram(ERD)는 시스템의 엔티티들이 무엇이 있는지, 어떤 관계가 있는지를 나타내는 다이어그램이다.
    
- 복합 키
    
    단일 열로는 테이블의 각 행을 유일하게 식별할 수 없는 경우가 있다. 이럴 때 여러 열을 조합하여 복합키를 만들면 유일성을 보장할 수 있다. 복합키는 특히 두 테이블 간의 다:다 관계를 관리할 때 유리하다.
    
    - 두개 이상의 컬럼을 묶어서 하나의 기본키로 지정하는것.
    - 기본키는 하나의 테이블에 하나만 존재할 수 있다, 또한 기본키는 하나 이상의 컬럼으로 구성되어 있다.
        - 기본키가 만약 복합키라면, 복합키 또한 당연히 유일성과 최소성을 만족해야한다.
- 연관관계
    
    ```
    One to one : 1 대 1 대응 / One to many : 1 대 다 대응 / Many to one : 다 대 1 대응 / Many to many : 다 대 다 대응
    ```
    
    - One to one : 1 대 1 대응
    - One to many : 1 대 다 대응
    - Many to one : 다 대 1 대응
    - Many to many : 다 대 다 대응
    
    출처:
    
    [https://inpa.tistory.com/entry/DB-📚-데이터-모델링-1N-관계-📈-ERD-다이어그램](https://inpa.tistory.com/entry/DB-%F0%9F%93%9A-%EB%8D%B0%EC%9D%B4%ED%84%B0-%EB%AA%A8%EB%8D%B8%EB%A7%81-1N-%EA%B4%80%EA%B3%84-%F0%9F%93%88-ERD-%EB%8B%A4%EC%9D%B4%EC%96%B4%EA%B7%B8%EB%9E%A8)
    
    [Inpa Dev 👨‍💻:티스토리]
    
- 정규화
    
    목적: 데이터의 중복 제거, 무결성 유지
    
    장점: 데이터의 일관성 및 무결성 향상
    
    단점: 복잡한 쿼리 필요, 읽기 성능 저하 가능
    
    [정보처리 실기_데이터베이스06강_정규화](https://youtu.be/RXQ1kZ_JHqg?si=f0OPsoOWnJXSbqca)
    
    - 강의 내용 정리
        
        정규화 - 함수의 종속성 특징에 따라 테이블을 분해해나가는 과정
        
        1. 제1정규형 : 릴레이션에 속한 모든 도메인이 원자값 
            1. 열에는 위-아래의 순서가 없다
            2. 행에는 좌-우의 순서가 없다
            3. 중복되는 열이 없다
            4. 모든 열과 행의 중복지점에 (열과 행의) 해당되는 분야에서 한 개의 값을 가진다.  → 각 열은 하나의 속성을 표현하며, 하나의 열에는 단일한 값만 존재해야 함을 의미한다.
            5. 모든 행은 규칙적이다.
        2. 제2정규형: 키가 아닌 모든 속성들이 기본키에 완전 함수 종속 
        3. 제3정규형:  키가 아닌 모든 속성들이 기본키에 이행적으로 함수 종속 되지 않은 릴레이션  → 별도의 테이블로 만들어줘야 함
        4. BCNF (강한 제3정규형): 릴레이션의 모든 결정자가 후보키인 릴레이션  → 후보키가 아닌 속성이 후보키를 결정짓게 해서는 안됨 
        5. 제4정규형: 릴레이션 R에서 다치종속 A→B가 성립하는 경우 다치종속의 제
        6. 제5정규형: 조인종속성 이용 
    
- 반 정규화
    
    목적: 성능 향상, 쿼리 단순화, 너무 많은 정규화는 복잡한 조인을 유발하기 때문에 
    
    장점: 데이터 접근 속도 향상
    
    단점: 데이터 중복으로 인한 무결성 문제 발생 가능
    
    일부 정규화 규칙을 깨는 것