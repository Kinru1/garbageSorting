package com.lin.garbagesorting;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Slf4j
@SpringBootApplication
//@Component("config")
//@ServletComponentScan

public class GarbageSortingApplication {

    public static void main(String[] args) {
        SpringApplication.run(GarbageSortingApplication.class,args);
        log.info("启动了");
    }


}
