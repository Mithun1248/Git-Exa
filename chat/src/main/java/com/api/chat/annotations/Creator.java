package com.api.chat.annotations;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
@Author
public @interface Creator {

    @AliasFor(
            annotation = Author.class
    )
    String name() default "";
}
