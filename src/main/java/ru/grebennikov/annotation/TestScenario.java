package ru.grebennikov.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface TestScenario {
    String title();
    String acceptanceCriteria();
    String precondition();
    String note() default "";
    TestStep[] steps();
}
