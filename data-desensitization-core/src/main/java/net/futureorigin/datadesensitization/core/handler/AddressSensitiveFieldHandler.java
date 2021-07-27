package net.futureorigin.datadesensitization.core.handler;

import net.futureorigin.datadesensitization.core.SensitiveFieldType;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * AddressSensitiveFieldHandler
 * <p>
 * 地址处理
 * 地址只显示到地区，不显示详细地址
 * 例子：上海市浦东新区****
 * </p>
 *
 * @author Leander Lee create on 2021/7/21.
 */
public class AddressSensitiveFieldHandler extends AbstractSensitiveFieldHandler {

    private static final int RIGHT = 10;
    private static final int LEFT = 6;

    @Override
    public String getSensitiveType() {
        return SensitiveFieldType.ADDRESS;
    }

    @Override
    public String handle(Object src) {
        if (Objects.isNull(src)) {
            return null;
        }
        String address = src.toString();
        int length = StringUtils.length(address);
        if (length > RIGHT + LEFT) {
            return StringUtils.rightPad(StringUtils.left(address, length - RIGHT), length, "*");
        }
        if (length <= LEFT) {
            return address;
        } else {
            return address.substring(0, LEFT + 1).concat("*****");
        }
    }
}
