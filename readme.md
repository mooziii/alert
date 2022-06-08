# 🚨 alert

Alert is a really simple, easy to use and blazing fast event listening utility. 

## Tutorial

### Add the dependency

The project is available on maven central.
Now you can add the dependency

#### Gradle (Kotlin)

```kotlin
implementation("me.obsilabor:alert:1.0.6")
```

#### Gradle (Groovy)

```groovy
implementation 'me.obsilabor:alert:1.0.6'
```
*To shade the dependency into your jar you probably want to use the shadow gradle plugin*

#### Maven

```xml
<dependency>
    <groupId>me.obsilabor</groupId>
    <artifactId>alert</artifactId>
    <version>1.0.5</version>
</dependency>
```
*For maven you probably have to use any plugin that does the same as shadow, i have no idea how maven works*

<details>
    <summary>Kotlin Tutorial</summary>

### Create a event

Create a event by extending from `Event` (or if the event should be cancellable extend from `Cancellable`)

Example:

```kotlin
class RabbitJumpEvent(val rabbit: Rabbit) : Cancellable() {}
```

Trigger the event using `EventManager.callEvent`. If you want to procces the event e.g. if it can be cancelled, you must store the event as a variable.

Example:

```kotlin
fun handleRabbitJumping(rabbit: Rabbit) {
    val event = RabbitJumpEvent(this)
    EventManager.callEvent(event)
    if(event.isCancelled) {
        return
    }
    rabbit.jump()
}
```

### Listen to a event

Create a listener just by creating a new class and in the init method, you can use the listen function just like in this example:

```kotlin
class RabbitJumpListener {
    
    init {
        subscribeToEvent<RabbitJumpEvent> {
            //TODO: Do something cool :)
        }
    }
}
```

*To priorize subscriptions, you can change the priority parameter.*

```kotlin
class RabbitJumpListener {

    init {
        subscribeToEvent<RabbitJumpEvent>(priority = EventPriority.HIGHEST) {
            //TODO: Do something cool :)
        }
    }
}
```
</details>


<details>
    <summary>Java Tutorial</summary>
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
    RabbitJumpEvent event = EventManager.callEvent(event);
    if(event.isCancelled()) {
        return;    
    }
    rabbit.jump();
}
```

### Listen to a event

Create a listener just by creating a new class and a method annotated with @Subscribe. Add the event you want to listen to as an parameter.

```java
public class RabbitJumpListener {
    
    @Subscribe
    public void onRabbitJump(RabbitJumpEvent event) {
        //TODO: Do something cool :)
    }
}
```

*To priorize subscriptions, just set the priority value of the @Subscribe annotation*

```java
public class RabbitJumpListener {
    
    @Subscribe(priority = EventPriority.HIGHEST)
    public void onRabbitJump(RabbitJumpEvent event) {
        //TODO: Do something cool :)
    }
}
```
</details>


