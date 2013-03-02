package ru.spbau.mayorov.task4;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Arseny Mayorov.
 *         Date: 02.03.13
 */
public abstract class Event {

    /** Listeners collection.
     * Every listener's performAction() method is called during fireEvent().
     */
    private List<ActionListener> listeners = new ArrayList<ActionListener>();

    /** Method saying if event is ready to fire.
     *
     * @return boolean saying whether event is ready or not.
     */
    public abstract boolean ready();

    /** Fire event.
     *  Every listener's performAction() method is called during fireEvent().
     *  If event is not ready nothing will happen.
     */
    public void fireEvent() {
        for (ActionListener listener: listeners) {
            listener.performAction();
        }
    }

    /** Adds listener to this event.
     *
     * @param actionListener listener.
     */
    public void addListener(final ActionListener actionListener) {
        listeners.add(actionListener);
    }
}
