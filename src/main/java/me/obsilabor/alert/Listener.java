package me.obsilabor.alert;

public interface Listener {

    /**
     * Override this with your own logic e.g. if you want to toggle listeners during runtime
     * @return whether the listener is active or not
     */
    default boolean isActive() {
        return true;
    }

}
