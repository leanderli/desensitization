package net.futureorigin.desensitization.core.handler;

import net.futureorigin.desensitization.core.SensitiveFieldType;
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

    private static final String PROVINCE = "省";
    private static final String CITY = "市";
    private static final String COUNTRY = "县";
    private static final String REGION = "区";
    private static final String SIGN = "*****";

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
        // 按省市县来处理
        if (address.contains(REGION) || address.contains(COUNTRY)) {
            if (address.contains(REGION)) {
                return address.substring(0, address.indexOf(REGION) + 1).concat(SIGN);
            }
            if (address.contains(COUNTRY)) {
                return address.substring(0, address.indexOf(COUNTRY) + 1).concat(SIGN);
            }
        } else if (address.contains(CITY)) {
            return address.substring(0, address.indexOf(CITY) + 1).concat(SIGN);
        } else if (address.contains(PROVINCE)) {
            return address.substring(0, address.indexOf(PROVINCE) + 1).concat(SIGN);
        }
        return address;
    }
}
