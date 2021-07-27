package net.futureorigin.datadesensitization.core.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.futureorigin.datadesensitization.core.SensitiveFieldType;
import net.futureorigin.datadesensitization.core.serializer.jackson.JacksonSensitiveFieldSerializer;

import java.lang.annotation.*;

/**
 * SensitiveField
 * <p>
 * 标注敏感数据脱敏处理注解
 * </p>
 *
 * @author Leander Lee create on 2021/7/20.
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@JacksonAnnotationsInside
@JsonSerialize(using = JacksonSensitiveFieldSerializer.class)
public @interface SensitiveField {

    /**
     * 当前对象Class
     *
     * @return Class
     */
    Class<?> clz() default SensitiveField.class;

    /**
     * 敏感数据类型
     *
     * @return SensitiveFieldType
     */
    String value() default SensitiveFieldType.NONE;

    /**
     * 是否自动处理，只有标记为True时才会让框架自动处理序列化
     *
     * @return 是否自动处理
     */
    boolean autoProcessing() default false;

}
