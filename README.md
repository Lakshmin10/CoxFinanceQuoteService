# CoxFinanceQuoteService(Cox Coding Test for Backend Developer)

### Run the Service

Please use the following command to run the micro api service.

``` 
mvn spring-boot:run 

```

### How to Test Service

a) Add Stocks to watch 

	REQUEST:
	HTTP-Method: POST
	URL: http://localhost:8080/api/stocks/
	Content-Type: application/json
	Body: {"symbol": "TSLA"}  
	
	RESPONSE:
	Status: 201
	Body: No Content
	
To run from command line, please use the following curl statement	

```
curl -X POST "http://localhost:8080/api/stocks/" -H "Content-Type: application/json" -d "{\"symbol\": \"TSLA\"}" -v

curl -X POST "http://localhost:8080/api/stocks/" -H "Content-Type: application/json" -d "{\"symbol\": \"EHTH\"}" -v
```

b) Delete Stocks from watch 

	REQUEST:
	HTTP-Method: DELETE
	URL: http://localhost:8080/api/stocks/
	Content-Type: application/json
	Body: {"symbol": "TSLA"}  
	
	Response:
	Status: 204
	Body: No Content
	
To run from command line, please use the following curl statement

```
curl -X DELETE "http://localhost:8080/api/stocks/" -H "Content-Type: application/json" -d "{\"symbol\": \"TSLA\"}" -v

curl -X DELETE "http://localhost:8080/api/stocks/" -H "Content-Type: application/json" -d "{\"symbol\": \"EHTH\"}" -v
```

c) Get all available prices of a watched stock symbol.  

	REQUEST:
	HTTP-Method: GET
	URL: http://localhost:8080/api/stocks/symbol/TSLA  
	
	Response:
	Status: 200
	Body Example: {"currency":"USD","price":1222.09,"priceAvg50":879.48334,"priceAvg200":721.5445}
	
To run from command line, please use the following curl statement

```
curl -X GET "http://localhost:8080/api/stocks/symbol/TSLA" -v

curl -X GET "http://localhost:8080/api/stocks/symbol/EHTH" -v
```

d) Get current price of a watched stock symbol.  

	REQUEST:
	HTTP-Method: GET
	URL: http://localhost:8080//api/stocks/symbol/TSLA/price
	
	Response:
	Status: 200
	Body Example: 1222.09
	
To run from command line, please use the following curl statement

```
curl -X GET "http://localhost:8080/api/stocks/symbol/TSLA/price" -v

curl -X GET "http://localhost:8080/api/stocks/symbol/EHTH/price" -v
```

e) Get 50 day average price of a watched stock symbol.  

	REQUEST:
	HTTP-Method: GET
	URL: http://localhost:8080//api/stocks/symbol/TSLA/priceAvg50
	
	RESPONSE:
	Status: 200
	Body Example: 879.48334
	
To run from command line, please use the following curl statement

```
curl -X GET "http://localhost:8080/api/stocks/symbol/TSLA/priceAvg50" -v

curl -X GET "http://localhost:8080/api/stocks/symbol/EHTH/priceAvg50" -v
```

f) Get 200 day average price of a watched stock symbol.  

	REQUEST:
	HTTP-Method: GET
	URL: http://localhost:8080//api/stocks/symbol/TSLA/priceAvg200
	
	RESPONSE:
	Status: 200
	Body Example: 721.5445
	
To run from command line, please use the following curl statement

```
curl -X GET "http://localhost:8080/api/stocks/symbol/TSLA/priceAvg200" -v

curl -X GET "http://localhost:8080/api/stocks/symbol/EHTH/priceAvg200" -v
```

g) Get prices of all watched stock symbols.  

	REQUEST:
	HTTP-Method: GET
	URL: http://localhost:8080/api/stocks/all-prices
	
	RESPONSE:
	Status: 200
	Body Example: [{"symbol":"TSLA","priceData":{"currency":"USD","price":1222.09,"priceAvg50":879.48334,"priceAvg200":721.5445}},{"symbol":"EHTH","priceData":{"currency":"USD","price":40.4,"priceAvg50":42.723057,"priceAvg200":52.81475}}]
	
To run from command line, please use the following curl statement

```
curl -X GET "http://localhost:8080/api/stocks/all-prices" -v

```
