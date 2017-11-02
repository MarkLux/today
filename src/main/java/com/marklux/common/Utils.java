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
        return UUID.randomUUID().toString().replace("-", "");
    }

    // 胡乱写的主随机数生成函数

    public int getRandom(int daySeed, int indexSeed) {
        int n = daySeed % 11117;
        for (int i = 0; i < 100 + indexSeed; i++) {
            n = n * n;
            n = n % 11117;
        }
        return n;
    }
}
