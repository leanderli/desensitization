package net.futureorigin.test.controller;

import net.futureorigin.datadesensitization.core.SensitiveFieldHandlerRegistry;
import net.futureorigin.test.client.clientobject.UserCO;
import net.futureorigin.test.common.BirthdaySensitiveFieldHandler;
import net.futureorigin.test.common.CustomAddressSensitiveFieldHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

/**
 * data-desensitization
 * <p></p>
 *
 * @author Leander Lee create on 2021/7/21.
 */
@RestController
@RequestMapping(path = "test")
public class DataDesensitizationTestController {

    static {
        SensitiveFieldHandlerRegistry.getRegistry().registerSensitiveFieldHandler(
                new BirthdaySensitiveFieldHandler()
        );
    }
    @GetMapping(path = "getUser")
    public UserCO getUser() {
        UserCO userCO = new UserCO();
        userCO.setId(UUID.randomUUID().toString());
        userCO.setUserName("User-" + UUID.randomUUID());
        userCO.setUserCnName("李华");
        userCO.setUserEnName("Washing·Lee");
        userCO.setIdCardNo(110101199003073271L);
        userCO.setTelephone("0108899988");
        userCO.setMobile(19088999988L);
        userCO.setEmail("lihua@hotmail.com");
        userCO.setAddress("上海市浦东新区迎宾大道6000号");
        userCO.setBankCardNo(62234027863018843L);
        userCO.setBankCoopNo(105873100086L);

        userCO.setStudentNo(202100011021L);

        userCO.setBirthday(new Date());

        return userCO;
    }

}
