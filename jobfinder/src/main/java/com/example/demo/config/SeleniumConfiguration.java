package com.example.demo.config;

import jakarta.annotation.PostConstruct;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeleniumConfiguration {

    private static final String CHROMEDRIVER_PATH="/home/djole/Desktop/chromedriver-linux64/chromedriver";



    @Bean
    public ChromeDriver driver(){
        System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_PATH);
        ChromeOptions options = new ChromeOptions();
      // options.addArguments("--headless");
        return new ChromeDriver(options);
    }
}
