package com.example.timemanagementapp.utils;

import java.sql.Timestamp;

public class TimeUtil {
    private TimeUtil() {
    }

    public static boolean isNotNull(Timestamp... timestamps) {
        for (Timestamp timestamp : timestamps) {
            if (timestamp == null) {
                return false;
            }
        }
        return true;
    }

    public static boolean isBefore(Timestamp t1, Timestamp t2) {
        if (isNotNull(t1, t2)) {
            System.out.println(t1.getTime() + " " + t2.getTime());
            return t1.before(t2);
        }
        return false;
    }
}
