package net.futureorigin.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * data-desensitization
 * <p></p>
 *
 * @author Leander Lee create on 2021/7/21.
 */
@SpringBootApplication(
        scanBasePackages = {
        }
)
public class TestAppliation {

    public static void main(String[] args) {
        SpringApplication.run(TestAppliation.class, args);
    }
}
