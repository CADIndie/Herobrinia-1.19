package me.sirlennox.herobrinia.utils;

public class TimeUtil {

    public long lastMS;

    public TimeUtil() {
        this.lastMS = System.currentTimeMillis();
    }

    public boolean hasReached(long ms) {
        return System.currentTimeMillis() >= this.lastMS + ms;
    }

    public void reset() {
        this.lastMS = System.currentTimeMillis();
    }

}
