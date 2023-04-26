package me.obsilabor.alert;

/**
 * Represents a cancellable event
 */
@SuppressWarnings("unused")
public abstract class Cancellable extends Event {

    private boolean isCancelled = false;

    /**
     * @return whether the event is cancelled
     */
    public boolean isCancelled() {
        return isCancelled;
    }

    /**
     * Used to cancel the event
     * @param cancelled whether the event should be cancelled or not
     */
    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }
}