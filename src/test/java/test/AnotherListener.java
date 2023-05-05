package test;

import me.obsilabor.alert.Subscribe;

public class AnotherListener {
    @Subscribe
    public void onAnother(AnotherEvent event) {
        System.out.println("Another event!");
    }
}
