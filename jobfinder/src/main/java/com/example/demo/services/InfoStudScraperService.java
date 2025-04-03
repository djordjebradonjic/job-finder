package com.example.demo.services;

import com.example.demo.model.InfoStudJob;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class InfoStudScraperService {

    private final String baseURL = "https://poslovi.infostud.com/oglasi-za-posao-developer-software-engineer/beograd?category%5B0%5D=5&dist=50";

    @Autowired
    ChromeDriver driver;

    public List<InfoStudJob> scrape() {
        List<InfoStudJob> jobs = new ArrayList<>();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        int jobNumber = 2;
        System.out.println("Loading page: " + baseURL);
        while (true) {
            try {
                driver.get(baseURL);

                System.out.println("Job number: " + jobNumber);
                String cssSelector = "#oglas_" + jobNumber;


                wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssSelector)));
                WebElement job = driver.findElement(By.cssSelector(cssSelector));
                System.out.println("Job with ID=" + jobNumber );
                jobNumber++;

            } catch (NoSuchElementException e) {
                System.out.println("No more jobs  to find");
                break;

            }
        }
        return jobs;
    }
}
