package com.noderland.dash;

import java.util.TimeZone;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.noderland.commons", "com.noderland.dash"})
public class DashServiceApplication {

  public static void main(String[] args) {
    TimeZone.setDefault(TimeZone.getTimeZone("Europe/Istanbul"));
    SpringApplication.run(DashServiceApplication.class, args);
  }
}
