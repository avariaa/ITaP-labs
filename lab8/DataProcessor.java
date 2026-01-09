package lab8;
import java.lang.annotation.*;

//Аннотация

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DataProcessor {
    String name() default "";
    String description() default "";
    int priority() default 0;
}