package test;

import me.obsilabor.alert.EventManager;
import me.obsilabor.alert.Subscribe;

public class TestMain {

    public static void main(String[] args) {
        EventManager.registerListener(new TestMain());
        EventManager.callEvent(new ExampleEvent());
    }

    @Subscribe
    public void onExample(ExampleEvent event) {
        System.out.println(event);
        // Nested calls
        EventManager.registerListener(new AnotherListener());
        EventManager.callEvent(new AnotherEvent());
    }
}
