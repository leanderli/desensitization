package net.futureorigin.datadesensitization.core.annotation;

import java.lang.annotation.*;

/**
 * data-desensitization
 * <p>
 * 脱敏注解
 * 一般该注解无需使用，当需要处理某些场景下脱敏，某些不脱敏时可以使用
 * </p>
 *
 * @author Leander Lee create on 2021/7/23.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Desensitization {

    /**
     * 是否脱敏，默认脱敏
     *
     * @return 是否脱敏
     */
    boolean value() default true;
}
