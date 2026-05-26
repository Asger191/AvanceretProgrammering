package reflection.annotations;

import reflection.annotations_solution.Log;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Role {
    String value(); // fx "admin", "user"
}
