package net.futureorigin.desensitization.spring.boot.autoconfigure;

import net.futureorigin.desensitization.core.SensitiveFieldHandler;
import net.futureorigin.desensitization.core.SensitiveFieldHandlerRegistry;
import net.futureorigin.desensitization.core.SensitiveObjectHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SensitiveHandlerAutoConfiguration
 * <p></p>
 *
 * @author Leander Lee create on 2021/7/27.
 */
@Configuration
@ConditionalOnClass(SensitiveObjectHandler.class)
@EnableConfigurationProperties(SensitiveHandlerProperties.class)
public class SensitiveHandlerAutoConfiguration {

    @Bean
    public SensitiveObjectHandler sensitiveObjectHandler(SensitiveHandlerProperties properties) {
        registerAdditionalHandler(properties);
        return SensitiveObjectHandler.getObjectHandler();
    }

    private void registerAdditionalHandler(SensitiveHandlerProperties properties) {
        SensitiveFieldHandlerRegistry handlerRegistry = SensitiveFieldHandlerRegistry.getRegistry();
        try {
            for (String additionalHandler : properties.getAdditionalHandlers()) {
                Class<?> clz = Class.forName(additionalHandler);
                handlerRegistry.registerSensitiveFieldHandler(
                        (SensitiveFieldHandler) clz.getDeclaredConstructor().newInstance());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
