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
import java.util.NoSuchElementException;

@Service
public class ScraperService {

    private static final String URL = "https://www.helloworld.rs/oglasi-za-posao/programiranje/beograd?sort=p_vreme_postavljanja_sort&senioritet%5B0%5D=1&senioritet%5B1%5D=2&cat=340";

    private final ChromeDriver driver;

    public ScraperService(ChromeDriver driver) {
        this.driver = driver;
    }

    public List<Job> scrape() {
        List<Job> jobs = new ArrayList<>();

        try {
            driver.get(URL);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

//            WebElement jobListingContainer= driver.findElement(By.cssSelector("space-y-6"));
//            List<WebElement> jobListing = jobListingContainer.findElements(By.tagName("article"));
//            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.relative.bg-white.shadow-md.dark\\:bg-gray-800.rounded-lg.overflow-hidden")));

           // List<WebElement> jobList = driver.findElements(By.cssSelector("div.relative.bg-white.shadow-md.dark\\:bg-gray-800.rounded-lg.overflow-hidden"));
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.relative.bg-white.shadow-md")));
            List<WebElement> jobList = driver.findElements(By.cssSelector("div.relative.bg-white.shadow-md"));
            System.out.println(jobList);
            for (WebElement jobElement : jobList) {



                /// /
                // Extract job title
                WebElement titleElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h3 a.__ga4_job_title")));
                String title = titleElement.getText().trim();

                // Extract company name
                String company = "/"; // Default value if company element is not found
                try {
                    WebElement companyElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h4 a.__ga4_job_company")));
                    company = companyElement.getText().trim();
                } catch (NoSuchElementException e) {
                    System.err.println("Company element not found for job: " + title + ". Setting company to '/'.");
                }

                // Extract location
                String location = "/"; // Default value if location element is not found
                try {
                    WebElement locationElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.flex.items-center.gap-1:nth-child(1) p.text-sm.font-semibold")));
                    location = locationElement.getText().trim();
                } catch (NoSuchElementException e) {
                    System.err.println("Location element not found for job: " + title + ". Setting location to '/'.");
                }

                // Extract expiry date
                String expiryDate = "/"; // Default value if expiry date element is not found
                try {
                    WebElement expiryElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.flex.items-center.gap-1:nth-child(2) p.text-sm.font-semibold")));
                    expiryDate = expiryElement.getText().trim();
                } catch (NoSuchElementException e) {
                    System.err.println("Expiry date element not found for job: " + title + ". Setting expiry date to '/'.");
                }

                // Extract tags
                List<WebElement> tagElements = jobElement.findElements(By.cssSelector("a.btn.btn-xs.btn-outline-white.jobtag.__jobtag.w-auto.__ga4_job_tech_tag"));
                StringBuilder tags = new StringBuilder();
                for (WebElement tagElement : tagElements) {
                    tags.append(tagElement.getText().trim()).append(", ");
                }
                if (tags.length() > 2) {
                    tags.delete(tags.length() - 2, tags.length()); // Remove the trailing ", "
                }

                // Extract seniority
                String seniority = "/"; // Default value if seniority element is not found
                try {
                    WebElement seniorityElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button.__quick_senioritet")));
                    seniority = seniorityElement.getText().trim();
                } catch (NoSuchElementException e) {
                    System.err.println("Seniority element not found for job: " + title + ". Setting seniority to '/'.");
                }

                // Print the extracted information
                System.out.println("Title: " + title);
                System.out.println("Company: " + company);
                System.out.println("Location: " + location);
                System.out.println("Expiry Date: " + expiryDate);
                System.out.println("Tags: " + tags.toString());
                System.out.println("Seniority: " + seniority);
                System.out.println("--------------------");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // Zatvori browser
                driver.quit();
            }

        return jobs;
    }
}
