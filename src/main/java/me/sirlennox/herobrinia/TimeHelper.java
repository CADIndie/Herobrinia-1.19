package me.sirlennox.herobrinia;

public class TimeHelper {

    public long lastMS;

    public TimeHelper() {
        this.lastMS = System.currentTimeMillis();
    }

    public boolean hasReached(long ms) {
        return System.currentTimeMillis() >= this.lastMS + ms;
    }

    public void reset() {
        this.lastMS = System.currentTimeMillis();
    }

}
