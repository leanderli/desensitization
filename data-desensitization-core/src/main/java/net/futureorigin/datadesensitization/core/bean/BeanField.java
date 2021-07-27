package net.futureorigin.datadesensitization.core.bean;

/**
 * BeanField
 * <p></p>
 *
 * @author Leander Lee create on 2021/7/27.
 */
public class BeanField {
    private String fieldName;

    private Class<?> fieldType;

    private Object fieldValue;

    public BeanField(String fieldName, Class<?> fieldType) {
        this.fieldName = fieldName;
        this.fieldType = fieldType;
    }

    public BeanField(String fieldName, Class<?> fieldType, Object fieldValue) {
        this.fieldName = fieldName;
        this.fieldType = fieldType;
        this.fieldValue = fieldValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Class<?> getFieldType() {
        return fieldType;
    }

    public void setFieldType(Class<?> fieldType) {
        this.fieldType = fieldType;
    }

    public Object getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(Object fieldValue) {
        this.fieldValue = fieldValue;
    }
}