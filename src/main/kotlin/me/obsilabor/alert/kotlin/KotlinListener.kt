package me.obsilabor.alert.kotlin

import me.obsilabor.alert.Event
import me.obsilabor.alert.Subscribe

/**
 * Used to register listeners from the subscribeToEvent function.
 */
class KotlinListener<T : Event>(
    private val isActiveCallback: () -> Boolean,
    private val handler: (T) -> Unit
) {

    /**
     * Triggers the isActiveCallback
     * @return whether the listener is enabled
     */
    fun isActive(): Boolean {
        return isActiveCallback.invoke()
    }

    /**
     * Executes the event
     */
    @Subscribe
    fun handleEvent(event: T) {
        handler.invoke(event)
    }

}