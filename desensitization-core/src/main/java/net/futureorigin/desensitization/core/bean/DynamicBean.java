package net.futureorigin.desensitization.core.bean;

import cn.hutool.core.util.ArrayUtil;
import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.beans.BeanMap;

/**
 * DynamicBean
 * <p></p>
 *
 * @author Leander Lee create on 2021/7/27.
 */
public class DynamicBean {
    /**
     * Bean实体Object
     */
    private Object object = null;
    /**
     * 属性map
     */
    private BeanMap beanMap = null;

    public DynamicBean() {
        super();
    }

    public DynamicBean(BeanField... beanFields) {
        //用一组属性生成实体Bean
        Object object = generateBean(beanFields);
        //用实体Bean创建BeanMap，以便可以设置和获取Bean属性的值
        this.beanMap = BeanMap.create(object);
        // 设置属性
        setBeanFieldValues(beanFields);
        this.object = object;
    }

    private void setBeanFieldValues(BeanField[] beanFields) {
        if (ArrayUtil.isNotEmpty(beanFields)) {
            for (BeanField beanField : beanFields) {
                beanMap.put(beanField.getFieldName(), beanField.getFieldValue());
            }
        }
    }

    /**
     * 给bean中的属性赋值
     *
     * @param property 属性名
     * @param value    值
     */
    public void setFieldValue(String property, Object value) {
        beanMap.put(property, value);
    }

    /**
     * 获取bean中属性的值
     *
     * @param property 属性名
     * @return 值
     */
    public Object getFieldValue(String property) {
        return beanMap.get(property);
    }

    /**
     * 得到该实体bean对象
     *
     * @return Object
     */
    public Object getGeneratedBean() {
        return this.object;
    }

    private Object generateBean(BeanField... beanFields) {
        //根据一组属性名和属性值的类型，动态创建Bean对象
        BeanGenerator generator = new BeanGenerator();
        if (ArrayUtil.isNotEmpty(beanFields)) {
            for (BeanField beanField : beanFields) {
                generator.addProperty(beanField.getFieldName(), beanField.getFieldType());
            }
        }
        //创建Bean
        return generator.create();
    }

}