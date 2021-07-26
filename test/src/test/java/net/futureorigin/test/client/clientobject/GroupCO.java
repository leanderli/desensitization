package net.futureorigin.test.client.clientobject;

import lombok.Data;
import net.futureorigin.datadesensitization.core.SensitiveFieldType;
import net.futureorigin.datadesensitization.core.annotation.Desensitization;
import net.futureorigin.datadesensitization.core.annotation.SensitiveField;

/**
 * data-desensitization
 * <p></p>
 *
 * @author Leander Lee create on 2021/7/26.
 */
@Data
@Desensitization
public class GroupCO extends AbstractCO {

    @SensitiveField(value = SensitiveFieldType.CN_NAME, clz = GroupCO.class)
    private String groupName;

    @SensitiveField(value = SensitiveFieldType.CN_NAME, clz = GroupCO.class)
    private String groupClass;

}
