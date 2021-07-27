package net.futureorigin.test;

import com.alibaba.fastjson.JSON;
import net.futureorigin.datadesensitization.core.bean.BeanField;
import net.futureorigin.datadesensitization.core.bean.DynamicBean;
import net.futureorigin.datadesensitization.core.util.DesensitizationUtils;
import net.futureorigin.test.client.clientobject.GroupCO;
import net.futureorigin.test.client.clientobject.UserCO;

import java.util.Date;
import java.util.UUID;

/**
 * data-desensitization
 * <p></p>
 *
 * @author Leander Lee create on 2021/7/27.
 */
public class Test {

    @org.junit.Test
    public void testDesensitization() {
        UserCO userCO = generateUser(0);
        System.out.println(JSON.toJSONString(DesensitizationUtils.desensitization(userCO)));

        GroupCO groupCO = new GroupCO();
        groupCO.setGroupName("TestGroup");
        groupCO.setGroupClass("ROLE");
        groupCO.setTelephone("0108899988");
        groupCO.setIndex(123456433333L);
        GroupCO returned = (GroupCO) DesensitizationUtils.desensitization(groupCO);
        System.out.println(JSON.toJSONString(returned));
    }

    @org.junit.Test
    public void testGenerateBean() {
        BeanField groupClass = new BeanField("groupClass", String.class, "group");
        BeanField groupName = new BeanField("groupName", String.class, "test");
        DynamicBean dynamicBean = new DynamicBean(groupClass, groupName);
        System.out.println(JSON.toJSONString(dynamicBean.getGeneratedBean()));
    }

    private UserCO generateUser(int index) {
        UserCO userCO = new UserCO();
        userCO.setId(UUID.randomUUID().toString());
        userCO.setUserName("User-" + UUID.randomUUID());
        userCO.setUserCnName("李华-" + index);
        userCO.setUserEnName("Washing·Lee-" + index);
        userCO.setIdCardNo(110101199003073271L + index);
        userCO.setTelephone("0108899988-" + index);
        userCO.setMobile(19088999988L + index);
        userCO.setEmail("lihua-" + index + "@hotmail.com");
        userCO.setAddress("上海市浦东新区迎宾大道6000号-" + index);
        userCO.setBankCardNo(62234027863018843L + index);
        userCO.setBankCoopNo(105873100086L + index);

        userCO.setStudentNo(202100011021L + index);

        userCO.setBirthday(new Date());
        return userCO;
    }
}
