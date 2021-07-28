package net.futureorigin.desensitization.spring.boot;

import net.futureorigin.desensitization.spring.boot.autoconfigure.SensitiveHandlerAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * EnableSensitiveHandler
 * <p></p>
 *
 * @author Leander Lee create on 2021/7/28.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(SensitiveHandlerAutoConfiguration.class)
public @interface EnableSensitiveHandler {
}
