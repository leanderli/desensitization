package net.futureorigin.desensitization.core.handler;

import net.futureorigin.desensitization.core.SensitiveFieldType;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * TelephoneSensitiveFieldHandler
 * <p>
 * 电话处理
 * 保留前两位后四位
 * </p>
 *
 * @author Leander Lee create on 2021/7/21.
 */
public class TelephoneSensitiveFieldHandler extends AbstractSensitiveFieldHandler {
    @Override
    public String getSensitiveType() {
        return SensitiveFieldType.TELEPHONE;
    }

    @Override
    public String handle(Object src) {
        if (Objects.isNull(src)) {
            return null;
        }
        String fixedPhone = src.toString();
        return StringUtils.left(fixedPhone, 2).concat(
                StringUtils.removeStart(
                        StringUtils.leftPad(
                                StringUtils.right(fixedPhone, 4),
                                StringUtils.length(fixedPhone), "*"), "***"));
    }
}
