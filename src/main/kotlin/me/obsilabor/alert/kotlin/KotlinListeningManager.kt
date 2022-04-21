package me.obsilabor.alert.kotlin

import me.obsilabor.alert.Event
import me.obsilabor.alert.EventManager

/**
 * Function to create a kotlin listener
 */
inline fun <reified T : Event> subscribeToEvent(
    noinline isActiveCallback: () -> Boolean = { true },
    noinline handleCallback: (T) -> Unit
) {
    EventManager.registerListener(KotlinListener(isActiveCallback, handleCallback))
}