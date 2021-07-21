package net.futureorigin.datadesensitization.core.handler;

import net.futureorigin.datadesensitization.core.SensitiveFieldType;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * data-desensitization
 * <p>
 * 电子邮件处理
 * 保留前缀第一个字母和后缀
 * 例：lihua@hotmail.com -> l****@hotmail.com
 * </p>
 *
 * @author Leander Lee create on 2021/7/21.
 */
public class EmailSensitiveFieldHandler extends AbstractSensitiveFieldHandler {
    @Override
    public String getSensitiveType() {
        return SensitiveFieldType.EMAIL;
    }

    @Override
    public String handle(Object src) {
        if (Objects.isNull(src)) {
            return null;
        }
        String email = src.toString();
        int index = StringUtils.indexOf(email, "@");
        if (index <= 1) {
            return email;
        } else {
            return StringUtils.rightPad(
                    StringUtils.left(email, 1), index, "*").concat(
                    StringUtils.mid(email, index, StringUtils.length(email)));
        }
    }
}
