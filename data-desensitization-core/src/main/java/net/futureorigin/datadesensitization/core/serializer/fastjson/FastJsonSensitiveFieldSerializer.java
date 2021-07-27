package net.futureorigin.datadesensitization.core.serializer.fastjson;

import cn.hutool.core.util.ReflectUtil;
import com.alibaba.fastjson.serializer.ValueFilter;
import net.futureorigin.datadesensitization.core.SensitiveFieldHandlerRegistry;
import net.futureorigin.datadesensitization.core.annotation.SensitiveField;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * FastJsonSensitiveFieldSerializer
 * <p>
 * 适配FastJson的敏感数据序列化
 * </p>
 *
 * @author Leander Lee create on 2021/7/22.
 */
public class FastJsonSensitiveFieldSerializer implements ValueFilter {

    @Override
    public Object process(Object object, String name, Object value) {
        if (Objects.isNull(object)) {
            return value;
        }
        Class<? extends Object> clz = object.getClass();
        try {
            Field field = ReflectUtil.getField(clz, name);
            if (null == field) {
                return value;
            }
            SensitiveField sensitiveField = field.getAnnotation(SensitiveField.class);
            if (null == sensitiveField) {
                return value;
            }
            boolean autoProcessing = sensitiveField.autoProcessing();
            if (!autoProcessing) {
                return value;
            }

            String sensitiveFieldType = sensitiveField.value();
            SensitiveFieldHandlerRegistry handlerRegistry = SensitiveFieldHandlerRegistry.getRegistry();
            if (handlerRegistry.alreadyBeSensitived(value)) {
                return value;
            }
            return handlerRegistry.getSensitiveFieldHandler(sensitiveFieldType).handle(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }
}
