package net.futureorigin.datadesensitization.core.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.futureorigin.datadesensitization.core.serializer.SensitiveFieldSerializer;

import java.lang.annotation.*;

/**
 * data-desensitization
 * <p>
 *      标注敏感数据脱敏处理注解
 * </p>
 *
 * @author Leander Lee create on 2021/7/20.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@JacksonAnnotationsInside
@JsonSerialize(using = SensitiveFieldSerializer.class)
public @interface SensitiveField {

    /**
     * 敏感数据类型
     * @return SensitiveType
     */
    String type();
}
