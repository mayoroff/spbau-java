package ru.spbau.mayorov.task4;


/**
 * Event becomes ready every 10 seconds.
 */
public class TimeEvent extends Event {

    /** Constant for number of msec in second. */
    private static long SECOND = 1000;

    /** Timestamp for the time when event will become ready. */
    private long readyTime = System.currentTimeMillis() + 10 * SECOND;


    @Override
    public final boolean ready() {
        return System.currentTimeMillis() > readyTime;
    }

    @Override
    public final void fireEvent() {
        readyTime = System.currentTimeMillis() + 10 * SECOND;
        super.fireEvent();
    }
}
