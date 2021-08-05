package net.futureorigin.desensitization.spring.boot.autoconfigure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * SensitiveHandlerProperties
 * <p></p>
 *
 * @author Leander Lee create on 2021/7/27.
 */
@ConfigurationProperties(
        prefix = SensitiveHandlerProperties.PROP_PREFIX
)
public class SensitiveHandlerProperties {
    static final String PROP_PREFIX = "net.futureorigin.desensitization";

    private String[] additionalHandlers;

    public String[] getAdditionalHandlers() {
        return additionalHandlers;
    }

    public void setAdditionalHandlers(String[] additionalHandlers) {
        this.additionalHandlers = additionalHandlers;
    }
}
