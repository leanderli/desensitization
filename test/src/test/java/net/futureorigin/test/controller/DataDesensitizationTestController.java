package net.futureorigin.test.controller;

import net.futureorigin.desensitization.core.SensitiveFieldHandlerRegistry;
import net.futureorigin.desensitization.core.handler.CommonNoSensitiveFieldHandler;
import net.futureorigin.desensitization.core.SensitiveObjectHandler;
import net.futureorigin.test.client.clientobject.GroupCO;
import net.futureorigin.test.client.clientobject.UserCO;
import net.futureorigin.test.common.BirthdaySensitiveFieldHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * data-desensitization
 * <p></p>
 *
 * @author Leander Lee create on 2021/7/21.
 */
@RestController
@RequestMapping(path = "test")
public class DataDesensitizationTestController extends SensitiveHandleController {

    static {
        SensitiveFieldHandlerRegistry.getRegistry().registerSensitiveFieldHandler(
                new BirthdaySensitiveFieldHandler(),
                new CommonNoSensitiveFieldHandler()
        );
    }

    @GetMapping(path = "getUser")
    public ResponseEntity<Object> getUser() {
        UserCO userCO = generateUser(0);

        return ResponseEntity.ok(nonDesensitization()
                ? userCO : SensitiveObjectHandler.desensitization(userCO));
    }

    @GetMapping(path = "getUserList")
    public ResponseEntity<Object> getUserList() {
        List<UserCO> userCOS = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            userCOS.add(generateUser(i));
        }

        return ResponseEntity.ok(nonDesensitization()
                ? userCOS : SensitiveObjectHandler.desensitization(userCOS));
    }

    @GetMapping(path = "getGroup")
    public GroupCO getGroup() {
        GroupCO groupCO = new GroupCO();
        groupCO.setGroupName("TestGroup");
        groupCO.setGroupClass("ROLE");
        groupCO.setTelephone("0108899988");

        return groupCO;
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
