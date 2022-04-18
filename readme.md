# 🚨 alert

Alert is a really simple and blazing fast event listening utility. 

## Tutorial

### Add the dependency

The project is available on maven central.
Now you can add the dependency

#### Gradle (Kotlin)

```kotlin
implementation("me.obsilabor:alert:1.0.2")
```

#### Gradle (Groovy)

```groovy
implementation 'me.obsilabor:alert:1.0.2'
```
*To shade the dependency into your jar you probably want to use the shadow gradle plugin*

#### Maven

```xml
<dependency>
    <groupId>me.obsilabor</groupId>
    <artifactId>alert</artifactId>
    <version>1.0.2</version>
</dependency>
```
*For maven you probably have to use any plugin that does the same as shadow, i have no idea how maven works*

### Create a event

Create a event by extending from `Event` (or if the event should be cancellable extend from `Cancellable`)

Example:

```java
public class RabbitJumpEvent extends Cancellable {
    
    private final Rabbit rabbit;
    
    public RabbitJumpEvent(Rabbit rabbit) {
        this.rabbit = rabbit;
    }
    
    public Rabbit getRabbit() {
        return this.rabbit;
    }
}
```

Trigger the event using `EventManager.callEvent`. If you want to procces the event e.g. if it can be cancelled, you must store the event as a variable.

Example:

```java
public void handleRabbitJumping(Rabbit rabbit) {
    RabbitJumpEvent event = new RabbitJumpEvent(this);
    EventManager.callEvent(event);
    if(event.isCancelled()) {
        return;    
    }
    rabbit.jump();
}
```

### Listen to a event

Create a listener by implementing the `Listener` interface.

```java
public class RabbitJumpListener implements Listener {
    
}
```

Now, create a method annotated with @Subscribe. Add the event you want to listen to as an parameter.

```java
public class RabbitJumpListener implements Listener {
    
    @Subscribe
    public void onRabbitJump(RabbitJumpEvent event) {
        //TODO: Do something cool :)
    }
}
```

*To priorize subscriptions, just set the priority value of the @Subscribe annotation*

```java
public class RabbitJumpListener implements Listener {
    
    @Subscribe(priority = 5) // 1 = lowest, 5 = highest
    public void onRabbitJump(RabbitJumpEvent event) {
        //TODO: Do something cool :)
    }
}
```
