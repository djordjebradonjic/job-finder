package com.example.demo.repository;

import com.example.demo.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job,Long> {

    List<Job> findTop25BySourceOrderByCreatedAtDesc(String source);
    boolean existsByTitleAndCompany_NameAndDetails_Location(String title, String companyName, String location);


}
