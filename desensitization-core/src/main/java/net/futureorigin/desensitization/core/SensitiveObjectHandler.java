package net.futureorigin.desensitization.core;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ReflectUtil;
import net.futureorigin.desensitization.core.annotation.SensitiveField;
import net.futureorigin.desensitization.core.bean.BeanField;
import net.futureorigin.desensitization.core.bean.DynamicBean;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * SensitiveObjectHandler
 * <p>
 * 敏感对象处理类
 * </p>
 *
 * @author Leander Lee create on 2021/7/27.
 */
public class SensitiveObjectHandler {

    public static Object desensitization(Object object) {
        if (Objects.isNull(object)) {
            throw new IllegalArgumentException("'object' must be not null");
        }
        if (object instanceof Map) {
            return object;
        } else if (object instanceof List) {
            List<Object> objectList = (List<Object>) object;
            if (CollectionUtil.isEmpty(objectList)) {
                return object;
            }

            List<Object> newObjectList = new ArrayList<>();
            for (Object o : objectList) {
                newObjectList.add(handleObject(o));
            }
            return newObjectList;
        } else {
            return handleObject(object);
        }
    }

    private static Object handleObject(Object object) {
        Class<?> clz = object.getClass();
        Field[] fields = ReflectUtil.getFields(clz);
        if (ArrayUtil.isEmpty(fields)) {
            return object;
        }
        boolean isValidType = validateFieldType(fields);
        List<BeanField> beanFields = new ArrayList<>();
        try {
            SensitiveFieldHandlerRegistry handlerRegistry = SensitiveFieldHandlerRegistry.getRegistry();
            for (Field field : fields) {
                field.setAccessible(true);
                SensitiveField sensitiveFieldAnno = field.getAnnotation(SensitiveField.class);
                if (null == sensitiveFieldAnno) {
                    if (!isValidType) {
                        beanFields.add(new BeanField(field.getName(), field.getType(), field.get(object)));
                    }
                    continue;
                }
                try {
                    Object beforeVal = field.get(object);
                    SensitiveFieldHandler fieldHandler = handlerRegistry.getSensitiveFieldHandler(sensitiveFieldAnno.value());
                    Object newVal = null;
                    if (null != fieldHandler) {
                        newVal = fieldHandler.handle(beforeVal);
                    }
                    if (!isValidType) {
                        beanFields.add(new BeanField(field.getName(), String.class,
                                null == newVal ? String.valueOf(beforeVal) : String.valueOf(newVal))
                        );
                    } else {
                        field.setAccessible(true);
                        field.set(object, newVal);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            if (CollectionUtil.isNotEmpty(beanFields)) {
                return new DynamicBean(beanFields.toArray(new BeanField[0])).getGeneratedBean();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    private static boolean validateFieldType(Field[] fields) {
        for (Field field : fields) {
            if (null == field.getAnnotation(SensitiveField.class)) {
                continue;
            }
            if (!field.getType().equals(String.class)) {
                return false;
            }
        }
        return true;
    }
}
