package com.deep.api.authorization.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Permit {
    String[] authorities() default {};
}
