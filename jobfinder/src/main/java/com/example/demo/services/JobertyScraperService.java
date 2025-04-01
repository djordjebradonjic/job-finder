package com.example.demo.services;


import com.example.demo.model.JobertyJob;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.*;

@Service
public class JobertyScraperService {

    private final String BaseUrl = "https://www.joberty.com/IT-jobs?domains=Backend,Data%20Science,Fullstack,Frontend&location=Serbia,Belgrade%20%28Serbia%29&page=1&seniority=Intermediate,Internship,Junior&sort=created";

    @Autowired
    ChromeDriver driver;

    public void scrape(){
        Set<JobertyJob> processedJobs = new HashSet<>();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try{
            boolean hasNextPage = true;
            int pageNumber = 0;
            String currentPageURL= BaseUrl;
            while(hasNextPage){
                driver.get(currentPageURL);
                System.out.println("Loading page: " + currentPageURL);
                System.out.println("Page numger: " + pageNumber + "**********************");

                wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.jbox.flex.flex-col.gap-4.drop-shadow-sm")));
                List<WebElement> jobList = driver.findElements(By.cssSelector("div.jbox.flex.flex-col.gap-4.drop-shadow-sm"));
                System.out.println("Number of jobd founded on page " + jobList.size());

                List<Map<String, String>> jobs = new ArrayList<>();

                for (WebElement jobElement : jobList) {
                    Map<String, String> job = new HashMap<>();

                    // Dohvati naziv posla (primjer za veće ekrane)
                    WebElement titleElement = jobElement.findElement(By.cssSelector("span.text-gray-900 > a"));
                    job.put("naziv_posla", titleElement.getText());
                    job.put("link_detalji", titleElement.getAttribute("href"));

                    // Dohvati naziv tvrtke
                    WebElement companyElement = jobElement.findElement(By.cssSelector("span.text-gray-500 > a"));
                    job.put("naziv_tvrtke", companyElement.getText());

                    jobs.add(job);
                }

                // Ispišite izvučene podatke
                for (Map<String, String> job : jobs) {
                    System.out.println(job);
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(driver != null)
                driver.close();
        }
    }
}
