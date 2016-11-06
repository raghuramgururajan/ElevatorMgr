package com.elevatormgr.web.controller;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.logging.log4j.*;
/**
 * Created by raghuram gururajan on 11/5/16.
 * A class that represents elevator service
 */
@Service
public class ElevatorService {
    final static Logger logger = LogManager.getLogger(ElevatorService.class);


    protected ConcurrentLinkedQueue<ElevatorRequest> requests = new ConcurrentLinkedQueue<ElevatorRequest>();
    protected Thread elevatorThread = null;
    private int maxLevel = 0;

    public boolean startElevator(int maxLevel) {
        elevatorThread = new Thread(new Elevator(maxLevel, requests));
        this.maxLevel = maxLevel;
        if (!elevatorThread.isAlive()) {
            logger.info("Starting Elevator "+ elevatorThread.getName());
            elevatorThread.start();
            return true;
        }

        logger.warn("Elevator is already started");
        return false;
    }

    public ElevatorRequest addRequestToElevator(ElevatorRequest elevatorRequest) {

        if(elevatorRequest!=null && elevatorRequest.getFromFloor() > 0 && elevatorRequest.getFromFloor() < this.getMaxLevel() && elevatorRequest.getToFloor() > 0 && elevatorRequest.getToFloor() <= this.getMaxLevel()) {
            requests.add(elevatorRequest);
            return requests.peek();
        }
        return null;
    }


    public int getMaxLevel() {
        return maxLevel;
    }

    public ElevatorRequest nextRequest() {
        if(requests.size()==0)
            return null;
        else
            return requests.remove();
    }


    public ElevatorRequest[] getAllRequests() {
        logRequests();
        return requests.toArray(new ElevatorRequest[0]);
    }

    public ElevatorRequest topRequest() {
        if(requests.size()==0)
            return null;
        return requests.peek();
    }

    public void logRequests() {
        logger.info(requests.toString());
    }

    public boolean isElevatorStarted() {
        if (elevatorThread == null || !elevatorThread.isAlive()) {
           return false;
        }
        return true;
    }
    public boolean shutDownElevator() {
        if(requests.isEmpty()) {
            elevatorThread.interrupt();
            return  true;
        }

        return false;
    }


}
