package net.futureorigin.datadesensitization.core.handler;

import net.futureorigin.datadesensitization.core.SensitiveFieldType;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * data-desensitization
 * <p>
 * 移动号码处理
 * 保留前三位后四位
 * </p>
 *
 * @author Leander Lee create on 2021/7/21.
 */
public class MobileSensitiveFieldHandler extends AbstractSensitiveFieldHandler {
    @Override
    public String getSensitiveType() {
        return SensitiveFieldType.MOBILE;
    }

    @Override
    public String handle(Object src) {
        if (Objects.isNull(src)) {
            return null;
        }
        String value = src.toString();
        return StringUtils.left(value, 3).concat(
                StringUtils.removeStart(
                        StringUtils.leftPad(
                                StringUtils.right(value, 4),
                                StringUtils.length(value), "*"), "***"));
    }
}
