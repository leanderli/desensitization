package net.futureorigin.test.common;

import net.futureorigin.desensitization.core.handler.AddressSensitiveFieldHandler;

import java.util.Objects;

/**
 * data-desensitization
 * <p></p>
 *
 * @author Leander Lee create on 2021/7/21.
 */
public class CustomAddressSensitiveFieldHandler extends AddressSensitiveFieldHandler {

    @Override
    public String handle(Object src) {
        if (Objects.isNull(src)) {
            return null;
        }
        return "CustomAddressSensitiveFieldHandler";
    }
}
