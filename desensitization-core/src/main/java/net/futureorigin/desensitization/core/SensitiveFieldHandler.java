package net.futureorigin.desensitization.core;

/**
 * SensitiveFieldHandler
 * <p>
 *     敏感数据处理接口
 * </p>
 *
 * @author Leander Lee create on 2021/7/20.
 */
public interface SensitiveFieldHandler {
    /**
     * 获取敏感数据类型
     * @return SensitiveFieldType
     */
    String getSensitiveType();

    /**
     * 处理敏感数据
     * @param src 源数据
     * @return 处理后的数据
     */
    String handle(Object src);
}
