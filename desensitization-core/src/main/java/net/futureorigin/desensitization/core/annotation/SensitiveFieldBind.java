package net.futureorigin.desensitization.core.annotation;

/**
 * SensitiveFieldBind
 * <p>
 * 敏感字段自定义数据绑定
 * </p>
 *
 * @author Leander Lee create on 2021/7/21.
 */
public @interface SensitiveFieldBind {

    /**
     * 自定义替换的数据
     * @return 自定义替换的数据
     */
    String bindedValue();

    /**
     * 自定义的序列化名称
     * @return 序列化名称
     */
    String serializedName() default "";
}
