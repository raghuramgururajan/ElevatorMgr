package com.elevatormgr.web.controller;

/**
 * Created by raghuram gururajan on 11/6/16.
 * A class that represents the response message for elevator
 */
public class ElevatorResponse {

    ElevatorRequest[] origElevatorReqs;
    String message;

    public ElevatorResponse(ElevatorRequest elevatorRequests[], String message) {
        this.origElevatorReqs = elevatorRequests;
        this.message = message;
    }

    public ElevatorRequest[] getOrigElevatorReq() {
        return origElevatorReqs;
    }

    public void setOrigElevatorReq(ElevatorRequest[] origElevatorReq) {
        this.origElevatorReqs = origElevatorReq;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
