package ru.spbau.mayorov.task4;

import java.util.Date;

/**
 * @author Arseny Mayorov.
 *         Date: 02.03.13
 */
public abstract class Main {

    /** Entry point.
     *
     * @param args - arguments passed from command line.
     */
    public static void main(final String[] args) {

        TimeEvent timeEvent = new TimeEvent();
        RandomEvent randomEvent = new RandomEvent();

        addListeners(timeEvent, randomEvent);

        while (true) {

            if (timeEvent.ready()) {
                timeEvent.fireEvent();
            }

            if (randomEvent.ready()) {
                randomEvent.fireEvent();
            }
        }
   }

    /** Adds listeners to events.
     *
     * @param timeEvent time event.
     * @param randomEvent random event.
     */
    private static void addListeners(final TimeEvent timeEvent,
                                     final RandomEvent randomEvent) {
        for (int i = 0; i < 3; i++) {

            final int subscriberNum = i + 1;

            timeEvent.addListener(new ActionListener() {

                @Override
                public void performAction() {
                    System.out.println("timeEvent subscriber " + subscriberNum);
                }
            });
        }

        for (int i = 0; i < 2; i++) {

            final int subscriberNum = i + 1;

            randomEvent.addListener(new ActionListener() {

                @Override
                public void performAction() {
                    System.out.println("randomEvent subscriber "
                            + subscriberNum);
                }
            });
        }
        for (int i = 0; i < 3; i++) {

            final long time = System.currentTimeMillis();

            timeEvent.addListener(new ActionListener() {

                @Override
                public void performAction() {
                    System.out.println("timeEvent subscriber "
                            + new Date(time).toString());
                }
            });
        }

        for (int i = 0; i < 2; i++) {

            final long time = System.currentTimeMillis();

            randomEvent.addListener(new ActionListener() {

                @Override
                public void performAction() {
                    System.out.println("randomEvent subscriber "
                            + new Date(time).toString());
                }
            });
        }
    }

}
