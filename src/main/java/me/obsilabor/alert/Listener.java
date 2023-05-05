package me.obsilabor.alert;

/**
 * Before 1.0.3 this represented a listener object.
 * @deprecated No longer required, just use normal classes without implementing something
 */
@Deprecated(since = "1.0.3", forRemoval = true)
public interface Listener {

    /**
     * Override this with your own logic e.g. if you want to toggle listeners during runtime
     * @return whether the listener is active or not
     */
    default boolean isActive() {
        return true;
    }

}
