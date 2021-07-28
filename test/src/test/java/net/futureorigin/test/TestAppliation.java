package net.futureorigin.test;

import net.futureorigin.desensitization.spring.boot.EnableSensitiveHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * data-desensitization
 * <p></p>
 *
 * @author Leander Lee create on 2021/7/21.
 */
@SpringBootApplication(
//        scanBasePackages = {
//                "net.futureorigin.desensitization", "net.futureorigin.test"
//        }
)
@EnableSensitiveHandler
public class TestAppliation {

    public static void main(String[] args) {
        SpringApplication.run(TestAppliation.class, args);
    }
}
