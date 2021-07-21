package net.futureorigin.datadesensitization.core.handler;

import net.futureorigin.datadesensitization.core.SensitiveFieldType;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * data-desensitization
 * <p>
 * 开户行航联号处理
 * 前四位明文，后面脱敏
 * </p>
 *
 * @author Leander Lee create on 2021/7/21.
 */
public class BankCoopNoSensitiveFieldHandler extends AbstractSensitiveFieldHandler {
    @Override
    public String getSensitiveType() {
        return SensitiveFieldType.BANK_COOP_NO;
    }

    @Override
    public String handle(Object src) {
        if (Objects.isNull(src)) {
            return null;
        }
        String snapCard = src.toString();
        return StringUtils.rightPad(
                StringUtils.left(snapCard, 4),
                StringUtils.length(snapCard), "*");
    }
}
