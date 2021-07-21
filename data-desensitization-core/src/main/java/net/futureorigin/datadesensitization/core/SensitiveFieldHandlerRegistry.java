package net.futureorigin.datadesensitization.core;

import net.futureorigin.datadesensitization.core.handler.*;
import org.apache.commons.lang3.ArrayUtils;

import java.util.HashMap;

/**
 * data-desensitization
 * <p>
 * 敏感数据处理器注册器
 * </p>
 *
 * @author Leander Lee create on 2021/7/20.
 */
public class SensitiveFieldHandlerRegistry {

    private static final HashMap<String, SensitiveFieldHandler> HANDLER_REG_CONTANINER = new HashMap<>(12);

    private SensitiveFieldHandlerRegistry() {
        initialRegistry();
    }

    public static SensitiveFieldHandlerRegistry getRegistry() {
        return SensitiveFieldHandlerRegistryHolder.registry;
    }

    private void initialRegistry() {
        registerSensitiveFieldHandler(
                new NoneSensitiveFieldHandler(),
                new CommonNoSensitiveFieldHandler(),
                new AddressSensitiveFieldHandler(),
                new BankCardNoSensitiveFieldHandler(),
                new BankCoopNoSensitiveFieldHandler(),
                new CnNameSensitiveFieldHandler(),
                new EnNameSensitiveFieldHandler(),
                new EmailSensitiveFieldHandler(),
                new IDCardNoSensitiveFiledHandler(),
                new TelephoneSensitiveFieldHandler(),
                new MobileSensitiveFieldHandler()
        );
    }

    public void registerSensitiveFieldHandler(SensitiveFieldHandler... sensitiveFieldHandlers) {
        if (ArrayUtils.isEmpty(sensitiveFieldHandlers)) {
            return;
        }
        for (SensitiveFieldHandler sensitiveFieldHandler : sensitiveFieldHandlers) {
            HANDLER_REG_CONTANINER.put(sensitiveFieldHandler.getSensitiveType(), sensitiveFieldHandler);
        }
    }

    public SensitiveFieldHandler getSensitiveFieldHandler(String sensitiveFieldType) {
        return HANDLER_REG_CONTANINER.get(sensitiveFieldType);
    }

    public boolean alreadyBeSensitived(Object src) {
        return null == src || src.toString().indexOf("*") > 0;
    }

    private static class SensitiveFieldHandlerRegistryHolder {
        public static SensitiveFieldHandlerRegistry registry = new SensitiveFieldHandlerRegistry();
    }

}
