package me.obsilabor.alert.kotlin

import me.obsilabor.alert.Event
import me.obsilabor.alert.EventManager
import me.obsilabor.alert.Subscribe

/**
 * Used to register listeners from the subscribeToEvent function.
 */
@Suppress("unused")
class KotlinListener<T : Event>(
    private val isActiveCallback: () -> Boolean,
    private val handler: (T) -> Unit,
    private val listenerPriority: Int
) {

    /**
     * Triggers the isActiveCallback
     * @return whether the listener is enabled
     */
    fun isActive(): Boolean {
        return isActiveCallback.invoke()
    }

    fun getPriority(): Int {
        return listenerPriority
    }

    /**
     * Executes the event
     */
    @Subscribe
    fun handleEvent(event: T) {
        handler.invoke(event)
    }

    /**
     * Unregisters the actual listener.
     */
    fun unregister() {
        EventManager.unregisterListener(this)
    }
}