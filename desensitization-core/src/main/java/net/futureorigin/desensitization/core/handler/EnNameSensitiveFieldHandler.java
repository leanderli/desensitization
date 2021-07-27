package net.futureorigin.desensitization.core.handler;

import net.futureorigin.desensitization.core.SensitiveFieldType;

import java.util.Objects;

/**
 * EnNameSensitiveFieldHandler
 * <p>
 * 英文名处理
 * </p>
 *
 * @author Leander Lee create on 2021/7/21.
 */
public class EnNameSensitiveFieldHandler extends AbstractSensitiveFieldHandler {
    @Override
    public String getSensitiveType() {
        return SensitiveFieldType.EN_NAME;
    }

    @Override
    public String handle(Object src) {
        if (Objects.nonNull(src)) {
            return src.toString();
        }
        return null;
    }
}
