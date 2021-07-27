package net.futureorigin.desensitization.core;

/**
 * SensitiveFieldType
 * <p>
 * 敏感字段类型
 * </p>
 *
 * @author Leander Lee create on 2021/7/20.
 */
public class SensitiveFieldType {
    /**
     * 不处理
     */
    public static final String NONE = "none";
    /**
     * 通用号码处理（保留前三位后四位）
     */
    public static final String COMMON_NO = "commonNo";
    /**
     * 中文姓名
     */
    public static final String CN_NAME = "cnName";
    /**
     * 英文姓名
     */
    public static final String EN_NAME = "enName";
    /**
     * 身份证号码
     */
    public static final String ID_CARD_NO = "idCardNo";
    /**
     * 电话号码
     */
    public static final String TELEPHONE = "telephone";
    /**
     * 移动电话
     */
    public static final String MOBILE = "mobile";
    /**
     * 通讯地址
     */
    public static final String ADDRESS = "address";
    /**
     * 电子邮件
     */
    public static final String EMAIL = "email";
    /**
     * 银行卡号
     */
    public static final String BANK_CARD_NO = "bankCardNo";
    /**
     * 开户行联行号
     */
    public static final String BANK_COOP_NO = "bankCoopNo";


}
