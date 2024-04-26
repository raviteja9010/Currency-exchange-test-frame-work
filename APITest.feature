Feature: API Testing

  Scenario: Verify API call is successful and returns valid price
    Given the API call is successful
    Then verify if the API response contains valid prices

  Scenario: Verify status code and status returned by the API response
    Given the API call is successful
    Then check the status code and status returned by the API response

  Scenario: Fetch the USD price against AED and ensure it is within the specified range
    Given the API call is successful
    Then the USD price against AED is in range of 3.6 to 3.7

  Scenario: Ensure API response time is not less than 3 seconds
    Given the API call is successful
    Then the API response time is not less than 3 seconds

  Scenario: Verify that 162 currency pairs are returned by the API
    Given the API call is successful
    Then verify that 162 currency pairs are returned by the API

  Scenario: Verify API response matches the JSON schema
    Given the API call is successful
    Then verify if the API response matches the JSON schema

  Scenario: Verify if the API response contains necessary provider information
    Given the API call is successful
    Then verify if the API response contains necessary provider information

  Scenario: Verify if the API response contains valid documentation URL
    Given the API call is successful
    Then verify if the API response contains valid documentation URL
