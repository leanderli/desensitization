package net.futureorigin.datadesensitization.core.handler;

import net.futureorigin.datadesensitization.core.SensitiveFieldType;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * data-desensitization
 * <p>
 * 通用号码处理
 * 保留前三后四
 * </p>
 *
 * @author Leander Lee create on 2021/7/21.
 */
public class CommonNoSensitiveFieldHandler extends AbstractSensitiveFieldHandler {
    @Override
    public String getSensitiveType() {
        return SensitiveFieldType.COMMON_NO;
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
