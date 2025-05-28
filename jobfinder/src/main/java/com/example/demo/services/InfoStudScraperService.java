package com.example.demo.services;

import com.example.demo.model.Company;
import com.example.demo.model.InfoStudJob;
import com.example.demo.model.Job;
import com.example.demo.model.JobDetails;
import com.example.demo.repository.JobRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class InfoStudScraperService {

    private final String baseURL = "https://poslovi.infostud.com/oglasi-za-posao-developer-software-engineer/beograd?category%5B0%5D=5&dist=50";
    List<InfoStudJob> jobs = new ArrayList<>();


    @Autowired
    ChromeDriver driver;

    @Autowired
    private JobRepository jobRepository;

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
                System.out.println("Extracting job: " + jobNumber);
                extractData(job);
                System.out.println("Finished job: " + jobNumber);
                jobNumber++;

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("No more jobs  to find");
                break;

            }

        }
        return jobs;
    }

    public void extractData(WebElement job) {
       String title = "/";
        try {
            WebElement titleElement = job.findElement(By.cssSelector(".job__title_container h2"));
            title = titleElement.getText().trim();
        }catch (Exception e){
            System.err.println("No title element");
        }

        String company = "/";
        try {
            WebElement companyElement = job.findElement(By.cssSelector(".job-company"));
            company = companyElement.getText().trim();
        }catch (Exception e){
            System.err.println("No company element");
        }
        String location= "/";
        try {
            WebElement locationElement = job.findElement(By.cssSelector(".job-location span"));
            location = locationElement.getText().trim();
        }catch (Exception e ){
            System.err.println("No location element");
        }
        String expirationDateString = "/";
        try {
            WebElement jobExpirationDateElement = job.findElement(By.cssSelector(".job-expiration"));
            expirationDateString = jobExpirationDateElement.getText().replaceAll("\\.$", "").trim();
        }catch (Exception e ){
            System.err.println("No expiration element");
        }
        LocalDate expirationDate = null;
        if (!expirationDateString.equals("/")) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                expirationDate = LocalDate.parse(expirationDateString, formatter);
            } catch (DateTimeParseException e) {
                System.err.println("Invalid date format: " + expirationDateString);
            }
        }
        List<String> tags = new ArrayList<>();
        try {
            WebElement tagElement = job.findElement(By.className("__job_tags"));
            List<WebElement> tagElements = tagElement.findElements(By.className("__jobtag"));
            for (WebElement tag : tagElements) {
                if (!tag.getText().trim().isEmpty()) {
                    tags.add(tag.getText().trim() + ", ");
                }
            }
        }catch (Exception e){
            System.err.println("No tags element");
        }



        String reviewText = "/";
        String numberOfReviews = "/";
        String companyReview = "";
        try {
            WebElement companyReviewElement = job.findElement(By.cssSelector(".job-card-company-review"));
            companyReview = companyReviewElement.getAttribute("href");
            reviewText = companyReviewElement.getText().trim();
            numberOfReviews = reviewText.split(" ")[0];
        } catch (Exception e) {
            numberOfReviews="0";
            System.out.println("No reviews for this company");

        }
        InfoStudJob infoStudJob = new InfoStudJob( title,  company,  location, expirationDate,  tags,  companyReview,  numberOfReviews);
        saveJob(infoStudJob);
        jobs.add(infoStudJob);
    }


    private void saveJob(InfoStudJob infoStudJob ){
        Company  company = new Company.CompanyBuilder(infoStudJob.getCompany())
                .numberOfReviews(Integer.parseInt(infoStudJob.getNumberOfReviews()))
                .reviewLink(infoStudJob.getCompanyReviewLink())
                .build();
        JobDetails details = new JobDetails.JobDetailsBuilder()
                .location(infoStudJob.getLocation())
                .expirationDate(infoStudJob.getExpirationDate())
                .build();
        Job job = new Job.JobBuilder(infoStudJob.getTitle())
                .details(details)
                .company(company)
                .tags(infoStudJob.getTags())
                .source("InfoStud")
                .createdAt(LocalDateTime.now())
                .build();
        if(job != null && jobRepository.existsByTitleAndCompany_NameAndDetails_Location(job.getTitle(),
                job.getCompany().getName(),
                job.getDetails().getLocation())){

            jobRepository.save(job);
        }else{
            System.out.println("Job already exist : title = "+ job.getTitle()
                    + " " + "company= " + job.getCompany().getName()
                    + " location= " + job.getDetails().getLocation());
        }


    }
}
