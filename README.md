Exchange Rate API Test Framework
This project contains a test framework for the Exchange Rate API, which returns the USD rates against multiple currencies.

Setup
Clone this repository.
Run mvn clean install to build the project.
Tests
The following tests are included in this framework:

testApiCallSuccess: Tests that the API call is successful and returns a valid response.
testUsdPriceAgainstAed: Tests that the USD price against the AED is within the range of 3.6 - 3.7.
testApiResponseTime: Tests that the API response time is not less than 3 seconds from the current time.
testCurrencyPairCount: Tests that 162 currency pairs are returned by the API.
testJsonSchema: Tests that the API response matches the JSON schema.
Code Structure
The code is structured as follows:

ExchangeRateApi: A class that contains methods for making API calls and checking the response.
ExchangeRateResponse: A POJO class that represents the response from the Exchange Rate API.
ExchangeRateApiTest: A test class that contains tests for the acceptance criteria.
Dependencies
Java 11
Maven
JUnit 5
OkHttp
Jackson