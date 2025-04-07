package com.example.demo.services;


import com.example.demo.model.Company;
import com.example.demo.model.Job;
import com.example.demo.model.JobDetails;
import com.example.demo.model.JobertyJob;
import com.example.demo.repository.JobRepository;
import org.modelmapper.ModelMapper;
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
   private  ChromeDriver driver;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JobRepository jobRepository;

    public List<JobertyJob> scrape(){
        List<JobertyJob> jobs = new ArrayList<>();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        int pageNumber = 1;
        try{

            String currentPageURL= BaseUrl;
                driver.get(currentPageURL);
                System.out.println("Loading page: " + currentPageURL);
                System.out.println("Page numger: " + pageNumber  );

                wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.jbox.flex.flex-col.gap-4.drop-shadow-sm")));
                List<WebElement> jobList = driver.findElements(By.cssSelector("div.jbox.flex.flex-col.gap-4.drop-shadow-sm"));
                System.out.println("Number of jobd founded on page " + jobList.size());



                for (WebElement jobElement : jobList) {

                    WebElement titleElement = jobElement.findElement(By.cssSelector("span.text-gray-900 > a"));
                    String title = titleElement.getText();
                    String detailsLink =  titleElement.getAttribute("href");

                    WebElement companyElement = jobElement.findElement(By.cssSelector("span.text-gray-500 > a"));
                    String company = companyElement.getText();

                    List<WebElement> tagElements= jobElement.findElements(By.cssSelector("span.inline-flex"));
                    List<String> tags= extractTags(tagElements);
                    JobertyJob newJob = new JobertyJob(title, company,detailsLink,tags);
                    saveJobertyJob(newJob);
                    System.out.println(newJob.toString());
                    jobs.add(newJob);

                }


                return jobs;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            if(driver != null)
                driver.close();
        }
    }



    public List<String> extractTags(List<WebElement> tagElements){
        List<String> tags= new ArrayList<>();
        for(WebElement element : tagElements){
            tags.add(element.getText());
        }
        return tags;
    }
    private void saveJobertyJob(JobertyJob jobertyJob){
        Company company = new Company.CompanyBuilder(jobertyJob.getCompany()).build();
        JobDetails details = new JobDetails.JobDetailsBuilder().url(jobertyJob.getDetailsLink()).build();

        Job job = new Job.JobBuilder(jobertyJob.getTitle())
                .details(details)
                .company(company)
                .tags(jobertyJob.getTags())
                .source("Joberty")
                .build();
        jobRepository.save(job);
    }
}
