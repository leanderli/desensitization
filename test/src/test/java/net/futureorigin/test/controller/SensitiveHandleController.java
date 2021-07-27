package net.futureorigin.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * data-desensitization
 * <p></p>
 *
 * @author Leander Lee create on 2021/7/27.
 */
@RestController
public class SensitiveHandleController {

    @Autowired
    private HttpServletRequest request;

    protected boolean nonDesensitization() {
        if (null == request.getHeader("Non-Desensitization")) {
            return false;
        }
        return Boolean.parseBoolean(request.getHeader("Non-Desensitization"));
    }


}
