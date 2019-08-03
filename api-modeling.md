# API Modeling
## 1. 도메인
- 시뮬레이션 : simulation
- 분석 : analysis
- 공통 : common
- 코드 : code

## 2. API 목록
### 1. 시뮬레이션 이력 조회
시뮬레이션 이력 조회 (페이징 파라미터 조절해서 최근 10건 조회하도록 구현)
#### Request
- URI    
    /api/simulation/histories

- Type
    GET

- Parameters
    - page (query) : 페이지 번호, default 0
    - size (query) : 페이지 크기, default 10

#### Response
```json
{
  "header": {
    "status": 0,
    "message": "message for api response"
  },
  "body": {
    "page": 1,
    "size": 5,
    "totalPage": 3,
    "simulationHistories": [
      {
          "simulationNumber": 1,
          "fileName": "AM_20190802_TCG.dat"
      }
    ]
  }
}
```
## 2. 분석 실행
## 3. 코드 조회 (셀렉트 박스에 채워줄 것)
## 4. 분석 이력 조회
## 5. 분석 데이터 조회
