package com.example.calebabbottcustomersupport;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackages = "com.example.calebabbottcustomersupport",
        excludeFilters = @ComponentScan.Filter(Controller.class))
public class RootConfig {
}