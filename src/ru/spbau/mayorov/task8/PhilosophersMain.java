package ru.spbau.mayorov.task8;

import java.util.ArrayList;

/** Runs dining philosophers subtask. */
public class PhilosophersMain {

    /** Number of philosophers (and sticks, too). */
    private static final int N = 5;

    /** Creates objects representing forks. */
    private static ArrayList<Object> createForks() {
        ArrayList<Object> forks = new ArrayList<Object>();
        for (int i = 0; i < N; i++) {
            forks.add(new Object());
        }
        return forks;
    }

    /** Creates N philosophers. */
    private static ArrayList<Runnable> createPhilosophers(ArrayList<Object> forks) {
        ArrayList<Runnable> phils = new ArrayList<Runnable>();
        for (int i = 0; i < N; i++) {
            phils.add(new Philosopher("Philosopher" + (i + 1), forks.get(i % N), forks.get((i + 1) % N)));
        }
        return phils;
    }

    /** Entry point. */
    public static void main(String[] args) {

        ArrayList<Object> forks = createForks();
        ArrayList<Runnable> phils = createPhilosophers(forks);

        for (Runnable r: phils) {
            new Thread(r).start();
        }

    }

}
