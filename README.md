# API Testing Framework

This project contains a test framework for API testing using Java and REST Assured.

## Overview

This framework is designed to test the functionality of an API that provides exchange rates against multiple currencies. It includes test cases to verify various aspects of the API response, such as status code, response time, currency rates, and JSON schema validation.

## Setup

### Prerequisites
- Java JDK 8 or higher installed
- Maven installed

### Installation
1. Clone the repository to your local machine.
2. Navigate to the project directory.
3. Run `mvn clean install` to install dependencies.

## Usage

### Running Tests
1. Open a terminal.
2. Navigate to the project directory.
3. Run `mvn test` to execute all tests.

### Test Reports
After running the tests, you can find the test reports in the `target` directory:
- Surefire reports: `target/surefire-reports`
- Cucumber reports: `target/cucumber-reports`

## Test Cases

### Acceptance Criteria
1. API call is successful and returns valid price.
2. Check the status code and status returned by the API response.
3. Fetch the USD price against AED and ensure it is within the specified range.
4. Ensure API response time is not less than 3 seconds.
5. Verify that 162 currency pairs are returned by the API.
6. Verify API response matches the JSON schema.
7. Verify if the API response contains necessary provider information.
8. Verify if the API response contains valid documentation URL.

## Framework Structure

- `com.example.api`: Contains the APIClient class to make API calls.
- `com.example.tests`: Contains test classes implementing test cases.
- `com.example.steps`: Contains step definitions for BDD approach.
- `src/test/resources`: Contains feature files for BDD scenarios.
- `target`: Contains generated test reports.

