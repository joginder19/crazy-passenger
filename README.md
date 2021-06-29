Crazy Passenger Application
===========================
*Joginder Singh, 28-Jun-2021*

Introduction
------------

This is a Java spring boot application which tries to solve the crazy passenger problem.

Running the Application
------------------------

The application source code can be downloaded from this Gitlab repository, and can be run in any suitable IDE like IntelliJ IDEA or Eclipse.


Calling API
------------
Once the application is running, the API can be discovered and run using the following swagger URL:
http://localhost:8080/swagger-ui.html

Request Type: PUT
URL: /api/v1/crazy-passenger/seatAllocations/{numberOfSimulations}/simulations

Input parameters:
numberOfSimulations - number of simulations required.
Logging - will log messages at the server with more details about seat allocations.
AirplaneBoarding - Json body to include 100 passengers and boarding seat information.

Output from the API will be the probability the last passenger got his/her own seat.


Automated Integration Test
-------------------------
The application can also be tested by running this spring boot integration test  ReservationControllerIntegrationTest.java. This test makes use of the pre-defined test data for 100 passengers and airplane seats.


Sample input data
------------------
The sample input data for 10 passengers if the application is being run from Swagger or Postman is as below:

{
  "passengers": [
    {
      "id": 1,
      "firstName": "string",
      "lastName": "string",
      "dob": "2021-06-28",
      "allocatedSeat": 1
    },
    {
      "id": 2,
      "firstName": "string",
      "lastName": "string",
      "dob": "2021-06-28",
      "allocatedSeat": 2
    },
    {
      "id": 3,
      "firstName": "string",
      "lastName": "string",
      "dob": "2021-06-28",
      "allocatedSeat": 3
    },
    {
      "id": 4,
      "firstName": "string",
      "lastName": "string",
      "dob": "2021-06-28",
      "allocatedSeat": 4
    },
    {
      "id": 5,
      "firstName": "string",
      "lastName": "string",
      "dob": "2021-06-28",
      "allocatedSeat": 5
    },
    {
      "id": 6,
      "firstName": "string",
      "lastName": "string",
      "dob": "2021-06-28",
      "allocatedSeat": 6
    },
    {
      "id": 7,
      "firstName": "string",
      "lastName": "string",
      "dob": "2021-06-28",
      "allocatedSeat": 7
    },
    {
      "id": 8,
      "firstName": "string",
      "lastName": "string",
      "dob": "2021-06-28",
      "allocatedSeat": 8
    },
    {
      "id": 9,
      "firstName": "string",
      "lastName": "string",
      "dob": "2021-06-28",
      "allocatedSeat": 9
    },
    {
      "id": 10,
      "firstName": "string",
      "lastName": "string",
      "dob": "2021-06-28",
      "allocatedSeat":10
    }
  ],
  "airplaneSeats": [
    1,2,3,4,5,6,7,8,9,10
  ]
}
