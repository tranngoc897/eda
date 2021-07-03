package vn.com.ibss.au;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableAuthorizationServer
@EnableResourceServer
@EnableAsync
@EnableRetry
@EnableScheduling
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true, proxyTargetClass = true)
//The prePostEnabled property enables Spring Security pre/post annotations
//The securedEnabled property determines if the @Secured annotation should be enabled
//The jsr250Enabled property allows us to use the @RoleAllowed annotation
// proxyTargetClass is set in order to have this working for RestController’s methods,
// because controllers are usually classes, not implementing any interfaces.
public class AuServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuServiceApplication.class, args);
    }

}
