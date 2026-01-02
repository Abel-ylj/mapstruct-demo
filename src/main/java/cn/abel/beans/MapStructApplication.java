package cn.abel.beans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author : ylj
 * create at:  2025/12/13
 */
@SpringBootApplication
@ComponentScan(basePackages = "cn.abel")    // convert不在启动类的子目录中
public class MapStructApplication {
    public static void main(String[] args) {
        SpringApplication.run(MapStructApplication.class, args);
    }
}