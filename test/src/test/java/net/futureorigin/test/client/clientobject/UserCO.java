package net.futureorigin.test.client.clientobject;

import lombok.Data;
import net.futureorigin.datadesensitization.core.SensitiveFieldType;
import net.futureorigin.datadesensitization.core.annotation.Desensitization;
import net.futureorigin.datadesensitization.core.annotation.SensitiveField;
import net.futureorigin.test.common.CustomSensitiveFieldType;

import java.util.Date;

/**
 * data-desensitization
 * <p></p>
 *
 * @author Leander Lee create on 2021/7/21.
 */
@Data
@Desensitization
public class UserCO extends BaseCO {

    private String userName;

    @SensitiveField(value = SensitiveFieldType.CN_NAME, clz = UserCO.class)
    private String userCnName;
    private String userEnName;

    @SensitiveField(value = SensitiveFieldType.ID_CARD_NO)
    private Long idCardNo;

    @SensitiveField(value = SensitiveFieldType.TELEPHONE)
    private String telephone;

    @SensitiveField(value = SensitiveFieldType.MOBILE)
    private Long mobile;

    @SensitiveField(value = SensitiveFieldType.ADDRESS)
    private String address;

    @SensitiveField(value = SensitiveFieldType.EMAIL)
    private String email;

    @SensitiveField(value = SensitiveFieldType.BANK_CARD_NO)
    private Long bankCardNo;

    @SensitiveField(value = SensitiveFieldType.BANK_COOP_NO)
    private Long bankCoopNo;

    @SensitiveField(value = SensitiveFieldType.COMMON_NO)
    private Long studentNo;

    @SensitiveField(value = CustomSensitiveFieldType.BIRTHDAY)
    private Date birthday;



}
