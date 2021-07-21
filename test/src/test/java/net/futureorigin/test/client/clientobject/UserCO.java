package net.futureorigin.test.client.clientobject;

import lombok.Data;
import net.futureorigin.datadesensitization.core.SensitiveFieldType;
import net.futureorigin.datadesensitization.core.annotation.SensitiveField;
import net.futureorigin.test.common.CustomSensitiveFieldType;

import java.io.Serializable;
import java.util.Date;

/**
 * data-desensitization
 * <p></p>
 *
 * @author Leander Lee create on 2021/7/21.
 */
@Data
public class UserCO implements Serializable {

    private String id;
    private String userName;
    private String userCnName;
    private String userEnName;

    @SensitiveField(type = SensitiveFieldType.ID_CARD_NO)
    private Long idCardNo;

    @SensitiveField(type = SensitiveFieldType.TELEPHONE)
    private String telephone;

    @SensitiveField(type = SensitiveFieldType.MOBILE)
    private Long mobile;

    @SensitiveField(type = SensitiveFieldType.ADDRESS)
    private String address;

    @SensitiveField(type = SensitiveFieldType.EMAIL)
    private String email;

    @SensitiveField(type = SensitiveFieldType.BANK_CARD_NO)
    private Long bankCardNo;

    @SensitiveField(type = SensitiveFieldType.BANK_COOP_NO)
    private Long bankCoopNo;

    @SensitiveField(type = SensitiveFieldType.COMMON_NO)
    private Long studentNo;

    @SensitiveField(type = CustomSensitiveFieldType.BIRTHDAY)
    private Date birthday;


}
