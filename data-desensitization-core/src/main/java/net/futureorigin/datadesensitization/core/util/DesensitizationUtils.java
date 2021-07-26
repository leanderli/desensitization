package net.futureorigin.datadesensitization.core.util;

import cn.hutool.core.annotation.AnnotationUtil;
import net.futureorigin.datadesensitization.core.annotation.Desensitization;

/**
 * data-desensitization
 * <p></p>
 *
 * @author Leander Lee create on 2021/7/23.
 */
public class DesensitizationUtils {

    public static void desensitization(Class clz) {
        if (null == clz) {
            throw new IllegalArgumentException("Class must be not null");
        }
        AnnotationUtil.setValue(clz.getAnnotation(Desensitization.class), "value", true);
    }

    public static void nonDesensization(Class clz) {
        if (null == clz) {
            throw new IllegalArgumentException("Class must be not null");
        }
        AnnotationUtil.setValue(clz.getAnnotation(Desensitization.class), "value", false);
    }
}
