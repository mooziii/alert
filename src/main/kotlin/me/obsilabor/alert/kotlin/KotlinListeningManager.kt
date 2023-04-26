package me.obsilabor.alert.kotlin

import me.obsilabor.alert.Event
import me.obsilabor.alert.EventManager

/**
 * Function to create a kotlin listener
 */
@Suppress("unused")
inline fun <reified T : Event> subscribeToEvent(
    noinline isActiveCallback: () -> Boolean = { true },
    priority: Int = -1,
    noinline handleCallback: (T) -> Unit,
): KotlinListener<T> {
    val listener = KotlinListener(isActiveCallback, handleCallback, priority)
    EventManager.registerListener(listener)
    return listener
}