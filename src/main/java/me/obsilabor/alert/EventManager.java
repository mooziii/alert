package me.obsilabor.alert;

import me.obsilabor.alert.kotlin.KotlinListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EventManager {

    private static final List<Object> listeners = new ArrayList<>();

    public static  <T extends Event> void callEvent(T event) {
        for (Object listener : listeners) {
            try {
                Method method = listener.getClass().getMethod("isActive");
                boolean isActive = (boolean) method.invoke(listener);
                if(!isActive) {
                    continue;
                }
            } catch (Exception ignored) {
                continue;
            }
            for (Method method : listener.getClass().getDeclaredMethods()) {
                Subscribe subscribe = method.getAnnotation(Subscribe.class);
                if(subscribe == null) {
                    continue;
                }
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
                    e.printStackTrace();
                }
            }
        }
    }

    public static void registerListener(Object listener) {
        listeners.add(listener);
    }

}
