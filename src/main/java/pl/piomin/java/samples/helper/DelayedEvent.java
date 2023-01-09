package pl.piomin.java.samples.helper;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public record DelayedEvent(long startTime, String msg) implements Delayed {

    public long getDelay(TimeUnit unit) {
        long diff = startTime - System.currentTimeMillis();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    public int compareTo(Delayed o) {
        return (int) (this.startTime - ((DelayedEvent) o).startTime);
    }

}
