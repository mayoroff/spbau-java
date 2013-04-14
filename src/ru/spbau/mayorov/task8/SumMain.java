package ru.spbau.mayorov.task8;

import java.util.Random;

/** Runs "array sum" subtask. */
public class SumMain {

    /** Array size */
    private static final int N = 20000;

    /** Random number generator. */
    private static final Random RND = new Random();

    /** Generates array of given size filled with random numbers < maxNum
     * @param size size of array
     * @param maxNum numbers in array are limited to this number
     * */
    private static int[] generateArray(int size, int maxNum) {
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = RND.nextInt(maxNum);
        }
        return arr;
    }

    /** Prints array to stdout. */
    private static void printArray(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            sb.append(" ");
        }
        System.out.println(sb.toString());
    }

    /** Sum numbers in single thread. */
    private static long singleThreadSum(int[] arr) {
        long sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }

    /** Entry point */
    public static void main(String[] args) {

        int[] array = generateArray(N, 30);

        printArray(array);

        SumWorkerThread r1 = new SumWorkerThread(array, 0, 4);
        SumWorkerThread r2 = new SumWorkerThread(array, 1, 4);
        SumWorkerThread r3 = new SumWorkerThread(array, 2, 4);
        SumWorkerThread r4 = new SumWorkerThread(array, 3, 4);

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        Thread t3 = new Thread(r3);
        Thread t4 = new Thread(r4);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            System.err.println("Program interrupted");
            return;
        }


        long sum = r1.getSum() + r2.getSum() + r3.getSum() + r4.getSum();
        System.out.println(singleThreadSum(array));
        System.out.println(sum);

    }

}
