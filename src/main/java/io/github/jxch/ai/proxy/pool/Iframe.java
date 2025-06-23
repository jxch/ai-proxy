package io.github.jxch.ai.proxy.pool;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Iframe {
    Class<? extends SelenidePage> value() default SelenidePage.class;
}
