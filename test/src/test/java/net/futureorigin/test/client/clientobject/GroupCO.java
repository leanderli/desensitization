package net.futureorigin.test.client.clientobject;

import lombok.Data;
import net.futureorigin.desensitization.core.SensitiveFieldType;
import net.futureorigin.desensitization.core.annotation.SensitiveField;

/**
 * GroupCO
 * <p></p>
 *
 * @author Leander Lee create on 2021/7/26.
 */
@Data
public class GroupCO extends AbstractCO {

    @SensitiveField(value = SensitiveFieldType.CN_NAME, autoProcessing = true)
    private String groupName;

    @SensitiveField(value = SensitiveFieldType.CN_NAME, autoProcessing = true)
    private String groupClass;

    @SensitiveField(value = SensitiveFieldType.CN_NAME, autoProcessing = true)
    private String telephone;

    @SensitiveField(value = SensitiveFieldType.COMMON_NO, autoProcessing = true)
    private Long index;

}
