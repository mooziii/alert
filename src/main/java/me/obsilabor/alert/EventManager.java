package me.obsilabor.alert;

import me.obsilabor.alert.kotlin.KotlinListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Manages all events
 */
public class EventManager {

    private static final HashMap<Object, List<Method>> listeners = new HashMap<>();

    /**
     * Calls an event and triggers all listeners (without showing exceptions)
     * @param event The event to call
     * @param <T> Type of the event
     */
    public static  <T extends Event> void callEvent(T event) {
        callEvent(event, false);
    }

    /**
     * Calls an event and triggers all listeners
     * @param event The event to call
     * @param printException Whether exceptions should be printed or not
     * @param <T> Type of the event
     */
    public static  <T extends Event> void callEvent(T event, boolean printException) {
        for (Object listener : listeners.keySet()) {
            try {
                Method method = listener.getClass().getMethod("isActive");
                boolean isActive = (boolean) method.invoke(listener);
                if(!isActive) {
                    continue;
                }
            } catch (Exception ignored) {}
            for (Method method : listeners.get(listener)) {
                if(method.getParameterCount() != 1) {
                    System.out.println("[ALERT]: @Subscribe method with more or less then 1 arguments can't be invoked: " + method.getName());
                    continue;
                }
                if(!Arrays.asList(method.getParameterTypes()).contains(event.getClass()) && !(listener instanceof KotlinListener<?>)) {
                    continue;
                }
                try {
                    method.invoke(listener, event);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    if(printException) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * Registers a new listener
     * @param listener the listener to register
     */
    public static void registerListener(Object listener) {
        HashMap<Method, Integer> priorityMap = new HashMap<>();
        for (Method method : listener.getClass().getDeclaredMethods()) {
            Subscribe subscribe = method.getAnnotation(Subscribe.class);
            if(subscribe == null) {
                continue;
            }
            priorityMap.put(method, subscribe.priority());
        }
        List<Method> list = priorityMap.entrySet().stream().sorted(Map.Entry.comparingByValue()).map(Map.Entry::getKey).collect(Collectors.toList());
        Collections.reverse(list);
        listeners.put(listener, list);
        System.out.println(listeners);
    }

    /**
    }

}
