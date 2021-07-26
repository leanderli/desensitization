package net.futureorigin.datadesensitization.core.serializer.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import net.futureorigin.datadesensitization.core.SensitiveFieldHandlerRegistry;
import net.futureorigin.datadesensitization.core.annotation.Desensitization;
import net.futureorigin.datadesensitization.core.annotation.SensitiveField;

import java.io.IOException;
import java.util.Objects;

/**
 * data-desensitization
 * <p>
 * 适配Jackson的敏感数据自定义序列化处理
 * </p>
 *
 * @author Leander Lee create on 2021/7/20.
 */
public class JacksonSensitiveFieldSerializer extends JsonSerializer<Object> implements ContextualSerializer {

    private Class mClass;
    private String mSensitiveFieldType;

    public JacksonSensitiveFieldSerializer() {
    }

    public JacksonSensitiveFieldSerializer(Class clz, String sensitiveFieldType) {
        this.mClass = clz;
        this.mSensitiveFieldType = sensitiveFieldType;
    }

    @Override
    public void serialize(Object object, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (Objects.isNull(object)) {
            return;
        }
        SensitiveFieldHandlerRegistry handlerRegistry = SensitiveFieldHandlerRegistry.getRegistry();
        if (handlerRegistry.alreadyBeSensitived(object)) {
            return;
        }
        boolean desensitization = true;
        if (null!= mClass && !mClass.equals(SensitiveField.class)) {
            Desensitization desensitizationAnno = (Desensitization) mClass.getAnnotation(Desensitization.class);
            if (null != desensitizationAnno) {
                desensitization = desensitizationAnno.value();
            }
        }
        if (!desensitization) {
            jsonGenerator.writeString(String.valueOf(object));
        } else {
            jsonGenerator.writeString(handlerRegistry
                    .getSensitiveFieldHandler(this.mSensitiveFieldType)
                    .handle(object));
        }
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        if (null != beanProperty) {
            SensitiveField sensitiveField = beanProperty.getAnnotation(SensitiveField.class);
            if (null == sensitiveField) {
                sensitiveField = beanProperty.getContextAnnotation(SensitiveField.class);
            }
            if (null != sensitiveField) {
                return new JacksonSensitiveFieldSerializer(sensitiveField.clz(), sensitiveField.value());
            }
            return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
        } else {
            return serializerProvider.findNullValueSerializer(null);
        }
    }
}
