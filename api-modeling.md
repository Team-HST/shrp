# API Modeling
## 1. 도메인
- 시뮬레이션 : simulation
- 분석 : analysis
- 공통 : common
- 코드 : code

## 2. API 목록
### 1. 시뮬레이션 이력 조회
- 시뮬레이션 이력 조회
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
## 2. 분석 데이터 초회
- 파일명, 지표를 파라미터로 받아 차트에 뿌려줄 데이터 생성, 응답
- 분석 데이터 조회한 이력 적재됨
#### Request
- URI    
    /api/analysis/{simulationNumber}/{index}

- Type
    GET

- Parameters
    - a

## 3. 분석 이력 조회
- `2. 분석 데이터 조회` 이력을 조회
#### Request
- URI    
    /api/analysis/histories

- Type
    GET

- Parameters
    - page (query) : 페이지 번호, default 0
    - size (query) : 페이지 크기, default 10

## 4. 공통코드 조회
- Application에서 사용하는 공통코드 조
#### Request
- URI    
    /api/codes/{groupCode}

- Type
    GET

- Parameters
    - groupCode (path) : 그룹코드

#### Response
```json
{
   "header":{
      "code":200,
      "message":"OK"
   },
   "body":{
      "groupCode":"100",
      "commonCodes":[
         {
            "groupCode": "100",
            "subCode": "101",
            "subName": "VEHS",
            "description": "방향"
         },
         {
            "groupCode": "100",
            "subCode": "102",
            "subName": "SPEEDAVGHARM",
            "description": "속도"
         }
      ]
   }
}
```