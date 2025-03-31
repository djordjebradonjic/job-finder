package com.example.demo.services;


import com.example.demo.model.Job;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.*;

@Service
public class ScraperService {

    private final String BaseURL = "https://www.helloworld.rs/oglasi-za-posao/programiranje/beograd?sort=p_vreme_postavljanja_sort&senioritet%5B0%5D=1&senioritet%5B1%5D=2&cat=340";

    private final ChromeDriver driver;

    public ScraperService(ChromeDriver driver) {
        this.driver = driver;
    }

    public List<Job> scrape() {
        List<Job> jobs = new ArrayList<>();
        Set<String> processedJobUrls = new HashSet<>();

        try {
            int pageNumber = 1;
            boolean hasNextPage = true;
            while (hasNextPage) {
                String URL = BaseURL + "&page=" + pageNumber;
                driver.get(URL);

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));


                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.relative.bg-white.shadow-md")));
                List<WebElement> jobList = driver.findElements(By.cssSelector("div.relative.bg-white.shadow-md"));
                System.out.println("Broj pronađenih oglasa na stranici: " + jobList.size());

                extractJobListings(jobList, wait,processedJobUrls);
                try {
                    wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                    WebElement nextPageLink = driver.findElement(By.cssSelector("div.pagination a[href] i.las.la-angle-right"));

                    if (nextPageLink.isDisplayed() && nextPageLink.isEnabled()) {
                        pageNumber++;
                    } else {
                        hasNextPage = false;
                    }
                } catch (NoSuchElementException | TimeoutException e) {
                    System.out.println("Nema sledeće stranice. Prekidam petlju.");
                    hasNextPage = false;
                }

            }


        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            driver.quit();
        }
        return jobs;
    }

    private void extractJobListings(List<WebElement> jobList, WebDriverWait wait, Set<String> processedJobUrls) {
        for (WebElement jobElement : jobList) {

            try {
                WebElement titleElement = jobElement.findElement(By.cssSelector("h3 a.__ga4_job_title"));

                String title = titleElement.getText().trim();
                String jobUrl = titleElement.getAttribute("href");
                if (!processedJobUrls.contains(jobUrl)) {
                    processedJobUrls.add(jobUrl);


                    String company = "/";
                    try {
                        WebElement companyElement = jobElement.findElement(By.cssSelector("h4 a.__ga4_job_company"));
                        company = companyElement.getText().trim();
                    } catch (NoSuchElementException e) {
                        System.err.println("Company element not found for job: " + title + ". Setting company to '/'.");
                    }

                    String location = "/";
                    try {
                        WebElement locationElement = jobElement.findElement(By.cssSelector("div.flex.items-center.gap-1:nth-child(1) p.text-sm.font-semibold"));
                        location = locationElement.getText().trim();
                    } catch (NoSuchElementException e) {
                        System.err.println("Location element not found for job: " + title + ". Setting location to '/'.");
                    }


                    String expiryDate = "/";
                    try {
                        WebElement expiryElement = jobElement.findElement(By.cssSelector("div.flex.items-center.gap-1:nth-child(2) p.text-sm.font-semibold"));
                        expiryDate = expiryElement.getText().trim();
                    } catch (NoSuchElementException e) {
                        System.err.println("Expiry date element not found for job: " + title + ". Setting expiry date to '/'.");
                    }

                    List<WebElement> tagElements = jobElement.findElements(By.cssSelector("a.btn.btn-xs.btn-outline-white.jobtag.__jobtag.w-auto.__ga4_job_tech_tag"));
                    StringBuilder tags = new StringBuilder();
                    for (WebElement tagElement : tagElements) {
                        tags.append(tagElement.getText().trim()).append(", ");
                    }
                    if (tags.length() > 2) {
                        tags.delete(tags.length() - 2, tags.length());
                    }

                    String seniority = "/";
                    try {
                        WebElement seniorityElement = jobElement.findElement(By.cssSelector("button.__quick_senioritet"));
                        seniority = seniorityElement.getText().trim();
                    } catch (NoSuchElementException e) {
                        System.err.println("Seniority element not found for job: " + title + ". Setting seniority to '/'.");
                    }


                    System.out.println("Title: " + title);
                    System.out.println("Company: " + company);
                    System.out.println("Location: " + location);
                    System.out.println("Expiry Date: " + expiryDate);
                    System.out.println("Tags: " + tags);
                    System.out.println("Seniority: " + seniority);

                }
            } catch (Exception e) {
                System.err.println("Error extracting information from a job listing: " + e.getMessage());
            }
        }
    }
}
