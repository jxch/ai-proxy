package io.github.jxch.ai.proxy.pool;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Page {

    String url() default "";

    Class<? extends SelenidePage> father() default SelenidePage.class;
}
