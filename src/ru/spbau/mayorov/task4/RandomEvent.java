package ru.spbau.mayorov.task4;

import java.util.Random;

/**
 * Random generator decides whether event is ready or not.
 * Each call of ready() calls random generator.
 */
public class RandomEvent extends Event {
    /** Random number generator. */
    private Random rnd = new Random();
    /** Ready flag. */
    private boolean ready = false;

    @Override
    public final boolean ready() {

        ready = rnd.nextBoolean() || ready;

        return ready;
    }

    @Override
    public final void fireEvent() {
        ready = false;
        super.fireEvent();
    }
}
