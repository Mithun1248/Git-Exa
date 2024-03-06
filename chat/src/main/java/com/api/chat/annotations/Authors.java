package com.api.chat.annotations;

import java.lang.annotation.*;
import java.util.List;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface Authors {
    Author[] value() default {};
}
