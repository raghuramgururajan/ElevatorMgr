package com.elevatormgr.web.controller;

import com.elevatormgr.web.ElevatorConstants;
import com.elevatormgr.web.util.ElevatorStates;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.*;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by raghuram gururajan on 11/5/16.
 * A class that represents elevator process
 */
public class Elevator implements Runnable {
    final static Logger logger = LogManager.getLogger(Elevator.class);
    private int maxLevel = 0;
    private int level;
    private ElevatorStates currentState;
    private ConcurrentLinkedQueue<ElevatorRequest> requests;

    public Elevator(int maxLevel, ConcurrentLinkedQueue<ElevatorRequest> requests) {
        this.maxLevel = maxLevel;
        level = 1;
        this.requests = requests;
        setCurrentState(ElevatorStates.IDLE);
    }

    public ElevatorStates getCurrentState() {
        return currentState;
    }

    public void setCurrentState(ElevatorStates currentState) {
        this.currentState = currentState;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    @Override
    public void run() {
        try {
            while (true) {
                ElevatorRequest elevatorRequest = requests.poll();
                if (elevatorRequest != null) {
                    if (elevatorRequest.getFromFloor() == elevatorRequest.getToFloor() || elevatorRequest.getFromFloor() > maxLevel || elevatorRequest.getToFloor() > maxLevel || elevatorRequest.getToFloor() < 0 || elevatorRequest.getFromFloor() < 0) {
                        logger.warn("Invalid request. SKIP " + elevatorRequest);
                    } else {
                        logger.info("Processing of Request " + elevatorRequest + "started");
                        Thread.sleep(ElevatorConstants.TRANSIT_TIME);
                        while (this.getLevel() != elevatorRequest.getFromFloor()) {
                            if (this.getLevel() > elevatorRequest.getFromFloor())
                                this.moveDown();
                            else if (this.getLevel() < elevatorRequest.getFromFloor())
                                this.moveUp();
                        }
                        this.openDoor();
                        this.closeDoor();
                        while (this.getLevel() != elevatorRequest.getToFloor()) {
                            if (this.getLevel() > elevatorRequest.getToFloor())
                                this.moveDown();
                            else if (this.getLevel() < elevatorRequest.getToFloor())
                                this.moveUp();
                        }
                        this.openDoor();
                        this.closeDoor();
                        logger.info("Processing of Request " + elevatorRequest + "completed");
                    }
                } else {
                    logger.info(Thread.currentThread().getName() + ": No Requests to process for elevator.Waiting for 10 secs");
                    Thread.sleep(10000);
                }

            }

        } catch (InterruptedException e) {
            logger.error("Elevator thread interrupted", e);
        }
    }

    public void moveUp() throws InterruptedException {
        setCurrentState(ElevatorStates.UP);
        printStatusInfo();
        Thread.sleep(ElevatorConstants.TRANSIT_TIME);
        this.setLevel(++this.level);
        setCurrentState(ElevatorStates.IDLE);
    }

    public void moveDown() throws InterruptedException {
        setCurrentState(ElevatorStates.DOWN);
        printStatusInfo();
        Thread.sleep(ElevatorConstants.TRANSIT_TIME);
        this.setLevel(--this.level);
        setCurrentState(ElevatorStates.IDLE);
    }

    public void openDoor() throws InterruptedException {
        setCurrentState(ElevatorStates.OPEN);
        this.printStatusInfo();
        Thread.sleep(ElevatorConstants.TRANSIT_TIME);
    }

    public void closeDoor() throws InterruptedException {
        setCurrentState(ElevatorStates.CLOSE);
        this.printStatusInfo();
        Thread.sleep(ElevatorConstants.TRANSIT_TIME);
    }


    void printStatusInfo() {
        logger.info(" Level:" + this.getLevel() + " Status:" + this.getCurrentState());

    }


}
