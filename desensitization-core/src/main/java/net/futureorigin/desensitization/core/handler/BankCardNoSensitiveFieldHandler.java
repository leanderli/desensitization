package net.futureorigin.desensitization.core.handler;

import net.futureorigin.desensitization.core.SensitiveFieldType;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * BankCardNoSensitiveFieldHandler
 * <p>
 * 银行卡号处理
 * 保留前四后四
 * 例：62234027863018843 -> 6223**********8843
 * </p>
 *
 * @author Leander Lee create on 2021/7/21.
 */
public class BankCardNoSensitiveFieldHandler extends AbstractSensitiveFieldHandler {
    @Override
    public String getSensitiveType() {
        return SensitiveFieldType.BANK_CARD_NO;
    }

    @Override
    public String handle(Object src) {
        if (Objects.isNull(src)) {
            return null;
        }
        String bankCard = src.toString();
        return StringUtils.left(bankCard, 4).concat(
                StringUtils.removeStart(
                        StringUtils.leftPad(
                                StringUtils.right(bankCard, 4),
                                StringUtils.length(bankCard), "*"), "***"));
    }
}
