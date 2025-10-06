package com.apache.apache.controller;

import com.apache.apache.domain.JobExecutionRecord;
import com.apache.apache.service.TicketAnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/analytics")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AnalyticsController {

    private final TicketAnalysisService ticketAnalysisService;

    /**
     * Endpoint to trigger Spark analysis job.
     * Example: POST http://localhost:8080/api/analytics/run
     */
    @PostMapping("/run")
    public ResponseEntity<JobExecutionRecord> runAnalysis() {
        System.out.println("📩 Received request to run ticket analysis job...");
        JobExecutionRecord record = ticketAnalysisService.processTicketData();
        return ResponseEntity.ok(record);
    }
}
