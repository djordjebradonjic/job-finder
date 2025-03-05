package exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason ="No such job found")
public class JobNotFoundException extends RuntimeException {

    private final Long jobId;

    public JobNotFoundException(Long jobId){
        super("Job not found with ID: " + jobId);
        this.jobId= jobId;
    }

    public Long getJobId() {
        return this.jobId;
    }
}
