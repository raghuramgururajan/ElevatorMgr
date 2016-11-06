# ElevatorMgr- Spring MVC maven application\n
Module to demonstrate elevator functionality\n
\n
Steps to execute the project\n
a)Build the project from root directory maven clean install\n
b)The project comes inbuilt with jetty server that can be started using command mvn jetty:run to deploy and run the web application\n
\n
The main components of the project are\n
a)Spring Controller layer for implementing RESTful services for handling elevator request\n
\n
b)Elevator Service layer to handle all functionality with regards to start,stop retrieve current request etc for elevator\n
\n
c)The Elevator thread that runs in the background and is initiated from service layer and handles all the actual processing of the elevator\n
\n
d)Swagger module that can be accessed at http://localhost:8080/Elevatorapp/swagger-ui.html provides an API playground.\n
\n
Restful API's\n
The main rest API's are as below\n
\n
a)Starting the Elevator\n
Endpoint : /Elevatorapp/Elevator/start\n
\n
Input :\n
{"maxLevel" : <any integer>}\n
\n
CURL command for the same :\n
curl -X POST --header "Content-Type: application/json" --header "Accept: application/json" -d "10" "http://localhost:8080/Elevatorapp/Elevator/start"\n
Full Qualified Url:http://localhost:8080/Elevatorapp/Elevator/start\n
\n
b)Stopping the elevator\n
Endpoint : /Elevatorapp/Elevator/stop\n
CURL command for the same :\n
curl -X POST --header "Content-Type: application/json" --header "Accept: application/json" "http://localhost:8080/Elevatorapp/Elevator/stop"\n
Full Qualified Url:http://localhost:8080/Elevatorapp/Elevator/stop\n
\n
c)Adding request to Elevator\n
Endpoint : /Elevatorapp/Elevator/request\n
Input :\n
{\n
      "fromFloor": 3,\n
      "toFloor": 6\n
}\n
CURL command for the same:\n
curl -X PUT --header "Content-Type: application/json" --header "Accept: application/json" -d "{\n
  \"fromFloor\": 3,\n
  \"toFloor\": 6\n
}" "http://localhost:8080/Elevatorapp/Elevator/request"\n
       \n
\n
d)Get the Top Request currently being processed by Elevator\n
Endpoint:/Elevatorapp/Elevator/request/top\n
Curl command for the same:\n
curl -X GET --header "Accept: application/json" "http://localhost:8080/Elevatorapp/Elevator/request/top"\n
\n
\n
E)Get all the request processed by elevator\n
Endpoint:/Elevatorapp/Elevator/request/all\n
Curl Command for the same :\n
curl -X GET --header "Accept: application/json" "http://localhost:8080/Elevatorapp/Elevator/request/all"\n
\n
Sample Workflow\n
Start the Service with max level 10\n
Send Request to navigate from floor 2 to floor 5\n
Send Request to navigate from  floor 5 to 3\n
Send Request to navigate from floor 3 to floor 3\n
\n
The console logs are as below\n
Timestamp = 2016-11-06 12:07:38.253 PST | Message=Processing of Request From Floor:2 To Floor:5started\n
Timestamp = 2016-11-06 12:07:39.255 PST | Message= Level:1 Status:UP\n
\n
Timestamp = 2016-11-06 12:07:40.256 PST | Message= Level:2 Status:OPEN\n
\n
Timestamp = 2016-11-06 12:07:41.257 PST | Message= Level:2 Status:CLOSE\n
\n
Timestamp = 2016-11-06 12:07:42.258 PST | Message= Level:2 Status:UP\n
\n
Timestamp = 2016-11-06 12:07:43.259 PST | Message= Level:3 Status:UP\n
\n
Timestamp = 2016-11-06 12:07:44.260 PST | Message= Level:4 Status:UP\n
\n
Timestamp = 2016-11-06 12:07:45.261 PST | Message= Level:5 Status:OPEN\n
\n
Timestamp = 2016-11-06 12:07:46.263 PST | Message= Level:5 Status:CLOSE\n
\n
Timestamp = 2016-11-06 12:07:47.264 PST | Message=Processing of Request From Floor:2 To Floor:5completed\n
\n
Timestamp = 2016-11-06 12:07:47.264 PST | Message=Processing of Request From Floor:5 To Floor:3started\n
\n
Timestamp = 2016-11-06 12:07:48.265 PST | Message= Level:5 Status:OPEN\n
\n
Timestamp = 2016-11-06 12:07:49.266 PST | Message= Level:5 Status:CLOSE\n
\n
Timestamp = 2016-11-06 12:07:50.267 PST | Message= Level:5 Status:DOWN\n
\n
Timestamp = 2016-11-06 12:07:51.268 PST | Message= Level:4 Status:DOWN\n
\n
Timestamp = 2016-11-06 12:07:52.269 PST | Message= Level:3 Status:OPEN\n
\n
Timestamp = 2016-11-06 12:07:53.270 PST | Message= Level:3 Status:CLOSE\n
\n
Timestamp = 2016-11-06 12:07:54.272 PST | Message=Processing of Request From Floor:5 To Floor:3completed\n
\n
Timestamp = 2016-11-06 12:07:54.272 PST | Message=Invalid request. SKIP From Floor:3 To Floor:3\n
