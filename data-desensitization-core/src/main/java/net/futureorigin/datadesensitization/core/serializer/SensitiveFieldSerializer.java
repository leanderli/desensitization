package net.futureorigin.datadesensitization.core.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import net.futureorigin.datadesensitization.core.SensitiveFieldHandlerRegistry;
import net.futureorigin.datadesensitization.core.annotation.SensitiveField;
import net.futureorigin.datadesensitization.core.annotation.SensitiveFieldBind;

import java.io.IOException;

/**
 * data-desensitization
 * <p>
 * 敏感数据自定义序列化处理
 * </p>
 *
 * @author Leander Lee create on 2021/7/20.
 */
public class SensitiveFieldSerializer extends JsonSerializer<Object> implements ContextualSerializer {

    private String mSensitiveFieldType;

    public SensitiveFieldSerializer() {}

    public SensitiveFieldSerializer(String sensitiveFieldType) {
        this.mSensitiveFieldType = sensitiveFieldType;
    }

    @Override
    public void serialize(Object object, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (null == object) {
            return;
        }
        SensitiveFieldHandlerRegistry handlerRegistry = SensitiveFieldHandlerRegistry.getRegistry();
        if (handlerRegistry.alreadyBeSensitived(object)) {
            return;
        }
        jsonGenerator.writeString(handlerRegistry
                .getSensitiveFieldHandler(this.mSensitiveFieldType)
                .handle(object));
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        if (null != beanProperty) {
            SensitiveField sensitiveField = beanProperty.getAnnotation(SensitiveField.class);
            if (null == sensitiveField) {
                sensitiveField = beanProperty.getContextAnnotation(SensitiveField.class);
            }
            if (null != sensitiveField) {
                return new SensitiveFieldSerializer(sensitiveField.type());
            }
            return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
        } else {
            return serializerProvider.findNullValueSerializer(null);
        }
    }
}
