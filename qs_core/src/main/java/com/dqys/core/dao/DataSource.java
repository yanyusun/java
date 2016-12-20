package com.dqys.core.dao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by yan on 16-12-19.
 */
@Target({ElementType.TYPE, ElementType.METHOD })
@Retention(RUNTIME)
public @interface DataSource {
    String value();
}
