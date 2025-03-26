package com.example.demo.services;


import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

@Service
public class ScraperService {

    private static final String URL= "https://www.helloworld.rs/oglasi-za-posao/programiranje";

    private final ChromeDriver driver;

    public ScraperService(ChromeDriver driver) {
        this.driver = driver;
    }
    public void scrape(final String value){
        driver.get(URL + value);
    }
}
