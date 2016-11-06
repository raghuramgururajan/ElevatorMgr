package com.elevatormgr.web.util;

import com.elevatormgr.web.controller.ElevatorRequest;

/**
 * Created by raghuram gururajan on 11/5/16.
 * A class that represents the validation
 */
public class ValidationUtil {

    public static boolean isElevatorLevelValid(String elevatorLevel) {
        if (elevatorLevel != null && !elevatorLevel.isEmpty()) {
            try {
                if (Integer.parseInt(elevatorLevel) >= 1) {
                    return true;
                }
            } catch (Exception e) {
                return false;
            }
        }

        return false;
    }

    public static boolean isValidElevatorRequest(ElevatorRequest elevatorRequest) {
        if (elevatorRequest != null && elevatorRequest.getToFloor() > 0 && elevatorRequest.getFromFloor() > 0) {
            return true;
        }
        return false;
    }
}
