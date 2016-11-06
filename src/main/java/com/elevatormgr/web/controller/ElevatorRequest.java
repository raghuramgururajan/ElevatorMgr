package com.elevatormgr.web.controller;

/**
 * Created by raghuram gururajan on 11/5/16.
 */
public class ElevatorRequest {
    private int fromFloor;
    private int toFloor;

    public ElevatorRequest() {

    }

    public ElevatorRequest(int fromFloor, int toFloor) {
        this.fromFloor=fromFloor;
        this.toFloor=toFloor;
    }

    public int getFromFloor() {
        return fromFloor;
    }

    protected void setFromFloor(int fromFloor) {
        this.fromFloor = fromFloor;
    }

    public int getToFloor() {
        return toFloor;
    }

    protected void setToFloor(int toFloor) {
        this.toFloor = toFloor;
    }

    @Override
    public String toString() {
        return "From Floor:" + this.fromFloor +" To Floor:"+ this.toFloor;
    }

    @Override
    public boolean equals(Object request) {
        ElevatorRequest elevatorRequest = (ElevatorRequest) request;
        if(this.fromFloor == elevatorRequest.getFromFloor() && this.toFloor == elevatorRequest.getToFloor())
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        return Math.abs(((Integer)this.fromFloor).hashCode() + ((Integer) this.toFloor).hashCode());
    }
}
