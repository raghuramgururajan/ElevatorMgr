package com.elevatormgr.web.controller;

import com.elevatormgr.web.util.ValidationUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * A class that represents the controller for elevator
 */
@Controller
@RequestMapping(value = "/Elevator")
@Api(value = "Elevator app", description = "Elevator app to process request for an elevator")
public class ElevatorController {
    @Autowired
    ElevatorService elevatorService;

    @RequestMapping(value = "/start", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ElevatorResponse> startElevator(@RequestBody String maxLevel) {
        String msg = "";
        if (ValidationUtil.isElevatorLevelValid(maxLevel)) {
            if (elevatorService.startElevator(Integer.parseInt(maxLevel)) == Boolean.TRUE) {
                msg = "Sucessfully started elevator";
                return new ResponseEntity<ElevatorResponse>(new ElevatorResponse(new ElevatorRequest[0], msg), HttpStatus.ACCEPTED);
            }
        }
        msg = "Invalid Request";
        return new ResponseEntity<ElevatorResponse>(new ElevatorResponse(new ElevatorRequest[0], msg), HttpStatus.BAD_REQUEST);

    }

    @RequestMapping(value = "/stop", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ElevatorResponse> stopElevator() {
        String msg = "";
            if (!elevatorService.isElevatorStarted()) {
                msg = "Elevator is already stopped";

            }
           else if (elevatorService.shutDownElevator() == Boolean.TRUE) {
                msg = "Sucessfully stopped elevator";
                return new ResponseEntity<ElevatorResponse>(new ElevatorResponse(new ElevatorRequest[0], msg), HttpStatus.ACCEPTED);
            }
           else {
                msg = "Elevator cannot be stopped now .Please try later";
            }

        return new ResponseEntity<ElevatorResponse>(new ElevatorResponse(new ElevatorRequest[0], msg), HttpStatus.CONFLICT);

    }

    @RequestMapping(value = "/request", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ElevatorResponse> createElevatorRequest(@RequestBody ElevatorRequest elevatorRequest) {
        String msg = "";
        if (!elevatorService.isElevatorStarted()) {
            msg = "Elevator is not started";
            return new ResponseEntity<ElevatorResponse>(new ElevatorResponse(new ElevatorRequest[]{elevatorRequest}, msg), HttpStatus.BAD_REQUEST);
        }
        if (ValidationUtil.isValidElevatorRequest(elevatorRequest)) {
            ElevatorRequest elevatorRequestAddedToQueue = null;
            if ((elevatorRequestAddedToQueue = elevatorService.addRequestToElevator(elevatorRequest)) != null) {
                msg = "Elevator request sucessfully added";
                return new ResponseEntity<ElevatorResponse>(new ElevatorResponse(new ElevatorRequest[]{elevatorRequest}, msg), HttpStatus.CREATED);
            }
        }
        msg = "Bad Request";
        return new ResponseEntity<ElevatorResponse>(new ElevatorResponse(new ElevatorRequest[0], msg), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/request/top", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ElevatorResponse> getElevatorReq() {
        String msg = "";
        if (!elevatorService.isElevatorStarted()) {
            msg = "Elevator is not started";

            return new ResponseEntity<ElevatorResponse>(new ElevatorResponse(new ElevatorRequest[0], msg), HttpStatus.BAD_REQUEST);
        }
        ElevatorRequest elevatorRequest = elevatorService.topRequest();
        ElevatorRequest[] elevatorRequests = null;
        if (elevatorRequest == null) {
            elevatorRequests = new ElevatorRequest[0];
        } else {
            elevatorRequests = new ElevatorRequest[]{elevatorRequest};
        }
        return new ResponseEntity<ElevatorResponse>(new ElevatorResponse(elevatorRequests, msg), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/request/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ElevatorResponse> getAllElevatorReq() {
        String msg = "";
        if (!elevatorService.isElevatorStarted()) {
            msg = "Elevator is not started";

            return new ResponseEntity<ElevatorResponse>(new ElevatorResponse(new ElevatorRequest[0], msg), HttpStatus.BAD_REQUEST);
        }
        ElevatorRequest elevatorRequestArr[] = elevatorService.getAllRequests();
        msg = "Successfully retrieved all elevator requests";
        return new ResponseEntity<ElevatorResponse>(new ElevatorResponse(elevatorRequestArr, msg), HttpStatus.ACCEPTED);
    }

}