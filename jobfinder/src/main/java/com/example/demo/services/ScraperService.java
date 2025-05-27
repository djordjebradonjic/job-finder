package com.example.demo.services;


import com.example.demo.model.Company;
import com.example.demo.model.HelloWorldJobs;
import com.example.demo.model.Job;
import com.example.demo.model.JobDetails;
import com.example.demo.repository.JobRepository;
import org.modelmapper.ModelMapper;
import org.openqa.selenium.*;
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
import java.util.*;
import java.util.NoSuchElementException;

@Service
public class ScraperService {

    private final String BaseURL = "https://www.helloworld.rs/oglasi-za-posao/programiranje/beograd?sort=p_vreme_postavljanja_sort&senioritet%5B0%5D=1&senioritet%5B1%5D=2&cat=340";

    private List<HelloWorldJobs> jobs = new ArrayList<>();

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private JobRepository jobRepository;



    public ScraperService() {

    }

    public List<HelloWorldJobs> scrape() {
        WebDriver driver = new ChromeDriver();
        Set<String> processedJobUrls = new HashSet<>();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            boolean hasNextPage = true;
            int pageNumber = 0;
            String currentPageUrl = BaseURL;
            while (hasNextPage) {
               // String currentPageUrl = BaseURL + "&page=" + pageNumber;
                driver.get(currentPageUrl);
                System.out.println("Loading page: " + currentPageUrl);
                System.out.println(pageNumber + "*****************************************************");
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.relative.bg-white.shadow-md")));

                List<WebElement> jobList = driver.findElements(By.cssSelector("div.relative.bg-white.shadow-md"));
                System.out.println("Number of jobd founded on page " + jobList.size());
                extractJobListings(jobList, processedJobUrls);
                pageNumber=pageNumber+30;
                WebElement nextLinkElement = findNextPageLink(wait, pageNumber);

                if (nextLinkElement != null) {
                    String nextLinkHref = nextLinkElement.getAttribute("href");
                    if (nextLinkHref != null && !nextLinkHref.isEmpty()) {
                        System.out.println(nextLinkHref + "*********SLEDECI LINK ZA STRANU*********************");
                        currentPageUrl = nextLinkHref;
                    } else {
                        hasNextPage = false;
                    }
                } else {
                    System.out.println("NEXT LINK ELEEMNT IS NULL -----------------------------------------");
                    hasNextPage = false;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
        return jobs;
    }

    private WebElement findNextPageLink(WebDriverWait wait, int currentPageNumber) {
        try {
            List<WebElement> pageLinks = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.pagination a")));
            for (WebElement link : pageLinks) {
                String href = link.getAttribute("href");
                System.out.println(href + "-------------------------------------------");
                if (href != null && href.contains("stranica/" + currentPageNumber)) {
                    return link;
                }
            }
            return null;
        } catch (Exception e) {
            System.err.println("Error finding link to the next page: " + e.getMessage());
            return null;
        }
    }

    private void extractJobListings(List<WebElement> jobList, Set<String> processedJobUrls) {
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
                        expiryDate = expiryElement.getText().replaceAll("\\.$", "").trim();
                        System.out.println(expiryDate);
                    } catch (NoSuchElementException e) {
                        System.err.println("Expiry date element not found for job: " + title + ". Setting expiry date to '/'.");
                    }
                    LocalDate expirationDate = null;
                    if (!expiryDate.equals("/")) {
                        try {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                            expirationDate = LocalDate.parse(expiryDate, formatter);
                        } catch (DateTimeParseException e) {
                            System.err.println("Invalid date format: " + expiryDate);
                        }
                    }

                    List<WebElement> tagElements = jobElement.findElements(By.cssSelector("div.flex.items-center.gap-2.flex-wrap > a"));
                    List<String> tags= new ArrayList<>();
                    for (WebElement tagElement : tagElements) {
                        tags.add(tagElement.getText().trim());
                    }


                    String seniority = "/";
                    try {
                        WebElement seniorityElement = jobElement.findElement(By.cssSelector("button.__quick_senioritet"));
                        seniority = seniorityElement.getText().trim();
                    } catch (NoSuchElementException e) {
                        System.err.println("Seniority element not found for job: " + title + ". Setting seniority to '/'.");
                    }

                    HelloWorldJobs job = new HelloWorldJobs(title, company, location, expirationDate, tags, seniority, jobUrl);
                    jobs.add(job);
                    saveHelloWorldJob(job);

                    System.out.println("Title: " + title);
                    System.out.println("Company: " + company);
                    System.out.println("Location: " + location);
                    System.out.println("Expiry Date: " + expiryDate);
                    System.out.println("Tags: " + tags);
                    System.out.println("Seniority: " + seniority);
                    System.out.println("URL: " + jobUrl);
                    System.out.println("--------------------");
                }
            } catch (Exception e) {
                System.err.println("Error extracting information from a job listing: " + e.getMessage());
            }
        }
    }

    private void saveHelloWorldJob(HelloWorldJobs helloWorldJob){
        Company company = new Company.CompanyBuilder(helloWorldJob.getCompany()).build();
        JobDetails jobDetails = new JobDetails.JobDetailsBuilder()
                .location(helloWorldJob.getLocation())
                .url(helloWorldJob.getUrl())
                .expirationDate(helloWorldJob.getExpiryDate())
                .seniority(helloWorldJob.getSeniority())
                .build();
        List<String> tags = helloWorldJob.getTags();

        Job job = modelMapper.map(helloWorldJob, Job.class);
        job.setCompany(company);
        job.setDetails(jobDetails);
        job.setTags(tags);
        job.setSource("HelloWorld");
        job.setCreatedAt(LocalDateTime.now());
        jobRepository.save(job);
    }
}
