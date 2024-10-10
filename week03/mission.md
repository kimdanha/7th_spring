- 홈화면
    
    ```json
    포인트 
    1. API Endpoint
    GET https://umc.com/users/users-id/points
    
    2. Response
    {
    "points" : "포인트" 
    }
    
    3. Request Header
    Authorization : accessToken (String)
    
    ```
    
    ```json
    받은 미션
    1. API Endpoint
    GET https://umc.com/users/users-id/missions
    
    2. Response
    {
    "location" : "안암동"
    "shop_name": "반이학생마라탕"
    "mission_body": "10000 이상의 식사시 500p 적립"
    "shop_category": "중식당"
    "d_day": "7"
    }
    
    3. Request Header
    Authorization : accessToken (String)
    ```
    
- 마이페이지
    
    ```json
    1. API Endpoint
    GET https://umc.com/users/users-id
    
    2. Response
    {
    "name" : "nickname012",
    "email": "dlskj@naver.com",
    "phone_num": {} //미인증
    "points": "2500"
    }
    
    3. Request Header
    Authorization : accessToken (String)
    ```
    
- 리뷰 작성
    
    ```json
    1. API Endpoint
    POST https://umc.com/users/users-id/reviews
    
    2. Request Body
    {
    	"nickName" : "nickname",
    	"score" : "별점"
    	"contents" : "리뷰내용"
    }
    
    3. Request Header
    Authorization : accessToken (String)
    ```
    
- 미션 목록 조회(진행중, 진행완료)
    
    ```json
    받은 미션
    1. API Endpoint
    GET https://umc.com/users/users-id/missions
    
    2. Response
    {
    "location" : "안암동"
    "shop_name": "반이학생마라탕"
    "mission_body": "10000 이상의 식사시 500p 적립"
    "shop_category": "중식당"
    "d_day": "7"
    }
    
    3. Request Header
    Authorization : accessToken (String)
    
    4. Query String
    status = completed & status = ongoing
    ```
    
- 미션 성공 누르기
    
    ```json
    1. API Endpoint
    PATCH https://umc.com/users/users-id/missions/missions-id
    
    2. Request Body
    {
    	"status" : "completed"
    }
    
    3. Request Header
    Authorization : accessToken (String)
    ```
    
- 회원가입하기 (소셜 로그인 고려 X)
    
    ```json
    1. API Endpoint
    POST https://umc.com/users
    
    2. Request Body
    {
    	"location" : "agree", 
    	"marketing" : "agree",
    	"name" : "nickname012",
    	"gender" : "female",
    	"adress" : "주소",
    	"food_prefer" : "한식"
    	}
    
    3. 	Request Header
    Content-Type: application/json
    	
    
    ```