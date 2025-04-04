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
    List<InfoStudJob> jobs = new ArrayList<>();

    @Autowired
    ChromeDriver driver;

    public List<InfoStudJob> scrape() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        int jobNumber = 2;
        System.out.println("Loading page: " + baseURL);
        driver.get(baseURL);

        while (true) {
            try {

                System.out.println("Job number: " + jobNumber);
                String cssSelector = "#oglas_" + jobNumber;


                wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssSelector)));
                WebElement job = driver.findElement(By.cssSelector(cssSelector));
                System.out.println("Job with ID=" + jobNumber);
                extractData(job);
                jobNumber++;

            } catch (Exception e) {
                System.out.println("No more jobs  to find");
                break;

            }
        }
        return jobs;
    }

    public void extractData(WebElement job) {
        WebElement titleElement = job.findElement(By.cssSelector(".job__title_container h2"));
        String title = titleElement.getText().trim();

        WebElement companyElement = job.findElement(By.cssSelector(".job-company"));
        String company = companyElement.getText().trim();

        WebElement locationElement = job.findElement(By.cssSelector(".job-location span"));
        String location = locationElement.getText().trim();

        WebElement jobExpirationDateElement = job.findElement(By.cssSelector(".job-expiration"));
        String expirationDate = jobExpirationDateElement.getText().trim();

        WebElement tagElement = job.findElement(By.className("__job_tags"));
        List<WebElement> tagElements = tagElement.findElements(By.className("__jobtag"));
        List<String> tags= new ArrayList<>();
        for (WebElement tag : tagElements) {
            if(!tag.getText().trim().isEmpty()) {
                tags.add(tag.getText().trim() + ", ");
            }
        }



        String reviewText = "/";
        String numberOfReviews = "/";
        String companyReview = "";
        try {
            WebElement companyReviewElement = job.findElement(By.cssSelector(".job-card-company-review"));
            companyReview = companyReviewElement.getAttribute("href");
            reviewText = companyReviewElement.getText().trim();
            numberOfReviews = reviewText.split(" ")[0];
        } catch (RuntimeException e) {
            System.out.println("No reviews for this company");

        }
        InfoStudJob infoStudJob = new InfoStudJob( title,  company,  location, expirationDate,  tags,  companyReview,  numberOfReviews);
        jobs.add(infoStudJob);
    }

}
