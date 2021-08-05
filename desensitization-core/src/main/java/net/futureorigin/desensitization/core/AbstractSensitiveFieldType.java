package net.futureorigin.desensitization.core;

/**
 * AbstractSensitiveFieldType
 * <p></p>
 *
 * @author Leander Lee create on 2021/8/5.
 */
public abstract class AbstractSensitiveFieldType {

    /**
     * 不处理
     */
    public static final String NONE = "none";
    /**
     * 通用号码处理（保留前三位后四位）
     */
    public static final String COMMON_NO = "commonNo";
}
