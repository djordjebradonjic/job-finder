package com.example.demo.services;


import com.example.demo.model.Job;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScraperService {

    private static final String URL = "https://www.helloworld.rs/oglasi-za-posao/programiranje";

    private final ChromeDriver driver;

    public ScraperService(ChromeDriver driver) {
        this.driver = driver;
    }

    public List<Job> scrape() {
        List<Job> jobs = new ArrayList<>();

        try {
            driver.get(URL);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("space-y-6")));

            WebElement jobListingContainer= driver.findElement(By.cssSelector("space-y-6"));
            List<WebElement> jobListing = jobListingContainer.findElements(By.tagName("article"));

            for (WebElement jobElement : jobListing) {
//                String title = jobElement.findElement(By.cssSelector("h2.job-title")).getText();
//                String company = jobElement.findElement(By.cssSelector("div.company-name")).getText();
//                String location = jobElement.findElement(By.cssSelector("div.location")).getText();
//                String link = jobElement.findElement(By.cssSelector("a")).getAttribute("href");
                String title = jobElement.findElement(By.cssSelector("h3")).getText();
                System.out.println("Job Title: " + title);

                String company = jobElement.findElement(By.cssSelector("h4.font-semibold.opacity-75")).getText();
                System.out.println("Company: " + company);
                String location = jobElement.findElement(By.cssSelector("span.location-class")).getText();
                System.out.println("Location: " + location);
                String link = "/";


                WebElement jobType = jobElement.findElement(By.cssSelector("span.job-type-class"));
                System.out.println("Job Type: " + jobType.getText());
                if (location.toLowerCase().contains("beograd") ||
                        title.toLowerCase().contains("junior") ||
                        title.toLowerCase().contains("medior") ||
                        title.toLowerCase().contains("praksa")) {

                    jobs.add(new Job(title, company, location, link));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jobs;
    }
}
