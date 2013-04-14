package ru.spbau.mayorov.task8;

/**
 * Represents main threads.
 */
public class SumThread implements Runnable {

    /** Stores link to array with numbers. */
    private int[] array = null;
    /** Offset of start element. */
    private final int offset;
    /** Step between elements. */
    private final int step;

    /** Holds sum. */
    private long sum = 0;

    /** Constructs sum main thread.
     * @param array link to array with numbers
     * @param offset offset of start element
     * @param step step between elements
     * */
    public SumThread(int[] array, int offset, int step) {
        this.array = array;
        this.offset = offset;
        this.step = step;
    }

    /* Entry point for thread. */
    @Override
    public void run() {
        SumWorkerThread r1 = new SumWorkerThread(array, offset, 3 * step);
        SumWorkerThread r2 = new SumWorkerThread(array, offset + step, 3 * step);
        SumWorkerThread r3 = new SumWorkerThread(array, offset + 2 * step, 3 * step);
        Thread worker1 = new Thread(r1);
        Thread worker2 = new Thread(r2);
        Thread worker3 = new Thread(r3);
        worker1.start();
        worker2.start();
        worker3.start();
        try {
            worker1.join();
            worker2.join();
            worker3.join();
        } catch (InterruptedException e) {
            System.err.println(this.toString() + " interrupted");
            return;
        }

        this.sum = r1.getSum() + r2.getSum() + r3.getSum();

    }

    /** Gets sum. */
    public long getSum() {
        return sum;
    }
}
