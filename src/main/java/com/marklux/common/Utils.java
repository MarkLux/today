package com.marklux.common;

import java.util.UUID;

/**
 * Created by mark on 17/11/1.
 */
public class Utils {
    public static long createTimestamp() {
        return System.currentTimeMillis();
    }

    public static String createUUID() {
        return UUID.randomUUID().toString().replace("-","");
    }
}
