package com.api.chat.annotations;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface Author {

    @AliasFor("name")
    String firstName() default "";

    String lastName() default "";

    String name() default "";

}
