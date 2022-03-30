package me.obsilabor.alert;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.MODULE)
public @interface Subscribe {


    /**
     *
     * 1 - lowest
     * 5 - highest
     */
    int priority() default 3;

}
