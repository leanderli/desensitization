package net.futureorigin.datadesensitization.core.handler;

import net.futureorigin.datadesensitization.core.SensitiveFieldType;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * data-desensitization
 * <p>
 * 身份证号处理
 * 保留前三位后四位
 * </p>
 *
 * @author Leander Lee create on 2021/7/21.
 */
public class IDCardNoSensitiveFiledHandler extends AbstractSensitiveFieldHandler {
    @Override
    public String getSensitiveType() {
        return SensitiveFieldType.ID_CARD_NO;
    }

    @Override
    public String handle(Object src) {
        if (Objects.isNull(src)) {
            return null;
        }
        String idCard = src.toString();
        return StringUtils.left(idCard, 3).concat(
                StringUtils.removeStart(
                        StringUtils.leftPad(
                                StringUtils.right(idCard, 4),
                                StringUtils.length(idCard), "*"), "***"));
    }
}
