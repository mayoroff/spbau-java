package ru.spbau.mayorov.task8;

/**
 * Represents worker thread.
 */
public class SumWorkerThread implements Runnable {

    /** Stores link to array with numbers. */
    private int[] array = null;
    /** Offset of start element. */
    private final int offset;
    /** Step between elements. */
    private final int step;

    /** Holds sum. */
    private long sum = 0;

    /** Constructs sum worker thread.
     * @param array link to array with numbers
     * @param offset offset of start element
     * @param step step between elements
     * */
    public SumWorkerThread(int[] array, int offset, int step) {
        this.array = array;
        this.offset = offset;
        this.step = step;
    }

    /* Entry point for thread. */
    @Override
    public void run() {
        for (int i = offset; i < array.length; i += step) {
            sum += array[i];
        }
    }
    /** Gets sum. */
    public long getSum() {
        return sum;
    }
}
