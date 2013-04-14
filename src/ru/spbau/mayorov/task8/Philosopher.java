package ru.spbau.mayorov.task8;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author Arseny Mayorov.
 *         Date: 13.04.13
 */
public class Philosopher implements Runnable{

    /** Synchronization object for left fork. */
    private Object leftFork = null;
    /** Synchronization object for right fork. */
    private Object rightFork = null;
    /** Philosopher's name. */
    private String name = null;
    /** Random number generator. */
    private final Random rnd = new Random();

    /** Constructs philosopher and shows him left and right forks.
     * @param name name of philosopher
     * @param rightFork synchronization object for right fork
     * @param leftFork synchronization object for left fork
     * */
    public Philosopher(String name, Object rightFork, Object leftFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.name = name;
    }

    /** Called when philosopher gets left fork. */
    private void getLeftFork() {
        System.out.println(name + " got left fork");
    }

    /** Called when philosopher gets right fork. */
    private void getRightFork() {
        System.out.println(name + " got right fork");
    }

    /** Called when philosopher got two forks and ready to eat.
     * @throws InterruptedException thrown if eating was interrupted.
     * */
    private void eat() throws InterruptedException {
        int maxEatingTime = 5;
        int n = rnd.nextInt(maxEatingTime);
        System.out.println(name + " eats for " + n + " secs");
        TimeUnit.SECONDS.sleep(n);
    }

    @Override
    public void run() {

        while (true) {
            synchronized (leftFork) {
                getLeftFork();
                synchronized (rightFork) {
                    getRightFork();
                    try {
                        eat();
                    } catch (InterruptedException e) {
                        System.err.println("Eating interrupted." + name + " starts new attempt.");
                    }
                }
            }
        }
    }
}
