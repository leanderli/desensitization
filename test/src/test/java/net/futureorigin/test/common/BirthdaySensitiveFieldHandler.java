package net.futureorigin.test.common;

import net.futureorigin.datadesensitization.core.handler.AbstractSensitiveFieldHandler;

import java.util.Objects;

/**
 * data-desensitization
 * <p></p>
 *
 * @author Leander Lee create on 2021/7/21.
 */
public class BirthdaySensitiveFieldHandler extends AbstractSensitiveFieldHandler {
    @Override
    public String getSensitiveType() {
        return CustomSensitiveFieldType.BIRTHDAY;
    }

    @Override
    public String handle(Object src) {
        if (Objects.isNull(src)) {
            return null;
        }
        String value = src.toString();
        return value.replaceAll(value, "********");
    }
}
