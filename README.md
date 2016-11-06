# ElevatorMgr- Spring MVC maven application
Module to demonstrate elevator functionality

Steps to execute the project
a)Build the project from root directory maven clean install
b)The project comes inbuilt with jetty server that can be started using command mvn jetty:run to deploy and run the web application

The main components of the project are
a)Spring Controller layer for implementing RESTful services for handling elevator request

b)Elevator Service layer to handle all functionality with regards to start,stop retrieve current request etc for elevator

c)The Elevator thread that runs in the background and is initiated from service layer and handles all the actual processing of the elevator

d)Swagger module that can be accessed at http://localhost:8080/Elevatorapp/swagger-ui.html provides an API playground.

Restful API's
The main rest API's are as below

a)Starting the Elevator
Endpoint : /Elevatorapp/Elevator/start

Input :
{"maxLevel" : <any integer>}

CURL command for the same :
curl -X POST --header "Content-Type: application/json" --header "Accept: application/json" -d "10" "http://localhost:8080/Elevatorapp/Elevator/start"
Full Qualified Url:http://localhost:8080/Elevatorapp/Elevator/start

b)Stopping the elevator
Endpoint : /Elevatorapp/Elevator/stop
CURL command for the same :
curl -X POST --header "Content-Type: application/json" --header "Accept: application/json" "http://localhost:8080/Elevatorapp/Elevator/stop"
Full Qualified Url:http://localhost:8080/Elevatorapp/Elevator/stop

c)Adding request to Elevator
Endpoint : /Elevatorapp/Elevator/request
Input :
{
      "fromFloor": 3,
      "toFloor": 6
}
CURL command for the same:
curl -X PUT --header "Content-Type: application/json" --header "Accept: application/json" -d "{
  \"fromFloor\": 3,
  \"toFloor\": 6
}" "http://localhost:8080/Elevatorapp/Elevator/request"
       

d)Get the Top Request currently being processed by Elevator
Endpoint:/Elevatorapp/Elevator/request/top
Curl command for the same:
curl -X GET --header "Accept: application/json" "http://localhost:8080/Elevatorapp/Elevator/request/top"


E)Get all the request processed by elevator
Endpoint:/Elevatorapp/Elevator/request/all
Curl Command for the same :
curl -X GET --header "Accept: application/json" "http://localhost:8080/Elevatorapp/Elevator/request/all"

Sample Workflow
Start the Service with max level 10
Send Request to navigate from floor 2 to floor 5
Send Request to navigate from  floor 5 to 3
Send Request to navigate from floor 3 to floor 3

The console logs are as below
Timestamp = 2016-11-06 12:07:38.253 PST | Message=Processing of Request From Floor:2 To Floor:5started
Timestamp = 2016-11-06 12:07:39.255 PST | Message= Level:1 Status:UP

Timestamp = 2016-11-06 12:07:40.256 PST | Message= Level:2 Status:OPEN

Timestamp = 2016-11-06 12:07:41.257 PST | Message= Level:2 Status:CLOSE

Timestamp = 2016-11-06 12:07:42.258 PST | Message= Level:2 Status:UP

Timestamp = 2016-11-06 12:07:43.259 PST | Message= Level:3 Status:UP

Timestamp = 2016-11-06 12:07:44.260 PST | Message= Level:4 Status:UP

Timestamp = 2016-11-06 12:07:45.261 PST | Message= Level:5 Status:OPEN

Timestamp = 2016-11-06 12:07:46.263 PST | Message= Level:5 Status:CLOSE

Timestamp = 2016-11-06 12:07:47.264 PST | Message=Processing of Request From Floor:2 To Floor:5completed

Timestamp = 2016-11-06 12:07:47.264 PST | Message=Processing of Request From Floor:5 To Floor:3started

Timestamp = 2016-11-06 12:07:48.265 PST | Message= Level:5 Status:OPEN

Timestamp = 2016-11-06 12:07:49.266 PST | Message= Level:5 Status:CLOSE

Timestamp = 2016-11-06 12:07:50.267 PST | Message= Level:5 Status:DOWN

Timestamp = 2016-11-06 12:07:51.268 PST | Message= Level:4 Status:DOWN

Timestamp = 2016-11-06 12:07:52.269 PST | Message= Level:3 Status:OPEN

Timestamp = 2016-11-06 12:07:53.270 PST | Message= Level:3 Status:CLOSE

Timestamp = 2016-11-06 12:07:54.272 PST | Message=Processing of Request From Floor:5 To Floor:3completed

Timestamp = 2016-11-06 12:07:54.272 PST | Message=Invalid request. SKIP From Floor:3 To Floor:3
