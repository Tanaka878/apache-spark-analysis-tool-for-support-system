package com.apache.apache.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnalyticsResult {
    
    private String id;
    private String analysisType;
    private LocalDateTime generatedAt;
    private LocalDateTime dataRangeStart;
    private LocalDateTime dataRangeEnd;
    private Map<String, Object> metrics;
    private String description;
    
    public AnalyticsResult(String analysisType, Map<String, Object> metrics) {
        this.analysisType = analysisType;
        this.metrics = metrics;
        this.generatedAt = LocalDateTime.now();
    }
}