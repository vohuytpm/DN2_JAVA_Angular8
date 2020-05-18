package com.rikkeisoft.vn.service;

import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.utils.Key;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import com.rikkeisoft.vn.job.EmailJob;
import com.rikkeisoft.vn.model.JobStatus;
import com.rikkeisoft.vn.payload.ScheduleEmailRequest;
import com.rikkeisoft.vn.payload.ScheduleEmailResponse;

@Service
public class ScheduleMailServiceImp {
    private final String groupName = "email-group";

    private final Scheduler scheduler;

    @Autowired
    public ScheduleMailServiceImp(SchedulerFactoryBean schedulerFactory) {
        this.scheduler = schedulerFactory.getScheduler();
    }

    public ResponseEntity<ScheduleEmailResponse> addNewJobs(ScheduleEmailRequest scheduleEmailRequest, ZonedDateTime dateTime) throws SchedulerException {
    	JobDetail jobDetail = buildJobDetail(scheduleEmailRequest);
        Trigger trigger = buildJobTrigger(jobDetail, dateTime);
        scheduler.scheduleJob(jobDetail, trigger);
        ScheduleEmailResponse scheduleEmailResponse = new ScheduleEmailResponse(true,
                jobDetail.getKey().getName(), jobDetail.getKey().getGroup(), "Email Scheduled Successfully!");
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(scheduleEmailResponse);
    }

//    public String addNewJob() throws SchedulerException {
//        String id = UUID.randomUUID().toString();
//        
//        JobDetail job = newJob(TestJob1.class)
//                .withIdentity(id, groupName)
//                // http://www.quartz-scheduler.org/documentation/quartz-2.2.x/configuration/ConfigJDBCJobStoreClustering.html
//                // https://stackoverflow.com/a/19270566/285571
//                .requestRecovery(true)
//                .build();
//
//        Trigger trigger =
//            newTrigger()
//                .withIdentity(id + "-trigger", groupName)
//                .startNow()
//                .withSchedule(
//                    simpleSchedule().withMisfireHandlingInstructionFireNow()
//                )
//                .build();
//        
//        scheduler.scheduleJob(job, trigger);
//
//        return id;
//    }

    public boolean deleteJob(String id) throws SchedulerException {
        JobKey jobKey = new JobKey(id, groupName);
        return scheduler.deleteJob(jobKey);
    }

    public List<String> getJobs() throws SchedulerException {
        return scheduler
            .getJobKeys(GroupMatcher.jobGroupEquals(groupName))
            .stream()
            .map(Key::getName)
            .sorted(Comparator.naturalOrder())
            .collect(Collectors.toList());
    }

    /**
     * 
     */
    public List<JobStatus> getJobsStatuses() throws SchedulerException {
        LinkedList<JobStatus> list = new LinkedList<>();
        for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobDetail.getKey());
            for (Trigger trigger : triggers) {
                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                if (Trigger.TriggerState.COMPLETE.equals(triggerState)) {
                    list.add(new JobStatus(jobKey.getName(), true));
                } else {
                    list.add(new JobStatus(jobKey.getName(), false));
                }
            }
        }
        list.sort(Comparator.comparing(o -> o.id));
        return list;
    }

    private JobDetail buildJobDetail(ScheduleEmailRequest scheduleEmailRequest) {
        JobDataMap jobDataMap = new JobDataMap();

        jobDataMap.put("email", scheduleEmailRequest.getEmail());
        jobDataMap.put("subject", scheduleEmailRequest.getSubject());
        jobDataMap.put("body", scheduleEmailRequest.getBody());

        return JobBuilder.newJob(EmailJob.class)
                .withIdentity(UUID.randomUUID().toString(), groupName)
                .withDescription("Send Email Job")
                .usingJobData(jobDataMap)
                .storeDurably()
                .build();
    }
    
    private Trigger buildJobTrigger(JobDetail jobDetail, ZonedDateTime startAt) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), groupName)
                .withDescription("Send Email Trigger")
                .startAt(Date.from(startAt.toInstant()))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
                .build();
    }
}
