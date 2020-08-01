package com.springboot1;

import com.springboot1.shiro.CustomRelam;
import groovy.transform.ToString;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot1ApplicationTests {

    @Test
    public void contextLoads() {
    }


    @Test
    public void CustomReamTest(){

        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();

        defaultWebSecurityManager.setRealm(new CustomRelam());

        SecurityUtils.setSecurityManager(defaultWebSecurityManager);

        UsernamePasswordToken admin = new UsernamePasswordToken("admin", "123456");

        Subject subject = SecurityUtils.getSubject();
        subject.login(admin);

        System.out.println(subject.isAuthenticated());
    }
}
