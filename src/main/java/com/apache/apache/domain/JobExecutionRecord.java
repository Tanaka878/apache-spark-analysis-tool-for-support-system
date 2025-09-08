package com.apache.apache.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobExecutionRecord {
    
    private String jobName;
    private LocalDateTime executionTime;
    private boolean success;
    private long durationMs;
    private String errorMessage;
    private long recordsProcessed;
    
    public JobExecutionRecord(String jobName, LocalDateTime executionTime, 
                            boolean success, long durationMs) {
        this.jobName = jobName;
        this.executionTime = executionTime;
        this.success = success;
        this.durationMs = durationMs;
    }
}