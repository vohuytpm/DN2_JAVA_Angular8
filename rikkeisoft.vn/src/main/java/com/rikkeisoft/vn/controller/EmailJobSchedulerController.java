package com.rikkeisoft.vn.controller;
import java.time.ZonedDateTime;
import java.util.List;

import javax.validation.Valid;

import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rikkeisoft.vn.model.JobStatus;
import com.rikkeisoft.vn.payload.ScheduleEmailRequest;
import com.rikkeisoft.vn.payload.ScheduleEmailResponse;
import com.rikkeisoft.vn.service.JobsService;
import com.rikkeisoft.vn.service.ScheduleMailServiceImp;

@RestController
public class EmailJobSchedulerController {
    private static final Logger logger = LoggerFactory.getLogger(EmailJobSchedulerController.class);

    @Autowired
    private JobsService jobsService;;
    
    private ScheduleMailServiceImp scheduleMailService;
    
    @Autowired
    private EmailJobSchedulerController(ScheduleMailServiceImp scheduleMailService) {
    	this.scheduleMailService = scheduleMailService;
    }
    @PostMapping("/scheduleEmail")
    public ResponseEntity<ScheduleEmailResponse> scheduleEmail(@Valid @RequestBody ScheduleEmailRequest scheduleEmailRequest) {
        try {
            ZonedDateTime dateTime = ZonedDateTime.of(scheduleEmailRequest.getDateTime(), scheduleEmailRequest.getTimeZone());
            if(dateTime.isBefore(ZonedDateTime.now())) {
                ScheduleEmailResponse scheduleEmailResponse = new ScheduleEmailResponse(false,
                        "dateTime must be after current time");
                return ResponseEntity.badRequest().body(scheduleEmailResponse);
            }
            return scheduleMailService.addNewJobs(scheduleEmailRequest, dateTime);
        } catch (SchedulerException ex) {
            logger.error("Error scheduling email", ex);

            ScheduleEmailResponse scheduleEmailResponse = new ScheduleEmailResponse(false,
                    "Error scheduling email. Please try later!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(scheduleEmailResponse);
        }
    }

//    private JobDetail buildJobDetail(ScheduleEmailRequest scheduleEmailRequest) {
//        JobDataMap jobDataMap = new JobDataMap();
//
//        jobDataMap.put("email", scheduleEmailRequest.getEmail());
//        jobDataMap.put("subject", scheduleEmailRequest.getSubject());
//        jobDataMap.put("body", scheduleEmailRequest.getBody());
//
//        return JobBuilder.newJob(EmailJob.class)
//                .withIdentity(UUID.randomUUID().toString(), "email-jobs")
//                .withDescription("Send Email Job")
//                .usingJobData(jobDataMap)
//                .storeDurably()
//                .build();
//    }

//    private Trigger buildJobTrigger(JobDetail jobDetail, ZonedDateTime startAt) {
//        return TriggerBuilder.newTrigger()
//                .forJob(jobDetail)
//                .withIdentity(jobDetail.getKey().getName(), "email-triggers")
//                .withDescription("Send Email Trigger")
//                .startAt(Date.from(startAt.toInstant()))
//                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
//                .build();
//    }
    // 
    
    @RequestMapping(value = "/jobs", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<String>> addJobs(@RequestParam(defaultValue = "10", required = false) int jobs)
        throws SchedulerException {
        List<String> ids = jobsService.addNewJobs(jobs);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(ids);
    }

    @RequestMapping(value = "/jobs/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<String>> deleteJob(@PathVariable("id") String id)
        throws SchedulerException {
        return ResponseEntity
            .status(jobsService.deleteJob(id)? HttpStatus.OK: HttpStatus.NOT_FOUND)
            .build();
    }

    @RequestMapping(value = "/jobs", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<String>> getJobs()
        throws SchedulerException {
        List<String> ids = jobsService.getJobs();
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(ids);
    }

    @RequestMapping(value = "/status/jobs", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<JobStatus>> getJobsStatuses()
        throws SchedulerException {
        List<JobStatus> ids = jobsService.getJobsStatuses();
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(ids);
    }
}
