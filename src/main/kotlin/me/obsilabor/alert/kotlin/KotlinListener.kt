package me.obsilabor.alert.kotlin

import me.obsilabor.alert.Event
import me.obsilabor.alert.Subscribe

class KotlinListener<T : Event>(
    private val isActiveCallback: () -> Boolean,
    private val handler: (T) -> Unit
) {

    fun isActive(): Boolean {
        return isActiveCallback.invoke()
    }

    @Subscribe
    fun handleEvent(event: T) {
        handler.invoke(event)
    }

}