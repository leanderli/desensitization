package net.futureorigin.desensitization.core.handler;

import net.futureorigin.desensitization.core.SensitiveFieldType;

/**
 * NoneSensitiveFieldHandler
 * <p>
 * 不处理敏感数据
 * </p>
 *
 * @author Leander Lee create on 2021/7/21.
 */
public class NoneSensitiveFieldHandler extends AbstractSensitiveFieldHandler {
    @Override
    public String getSensitiveType() {
        return SensitiveFieldType.NONE;
    }

    @Override
    public String handle(Object src) {
        return null != src ? String.valueOf(src) : null;
    }
}
