package com.apache.apache.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "analytics")
@Data
public class AnalyticsProperties {
    
    private Job job = new Job();
    private int batchSize = 10000;
    private Incremental incremental = new Incremental();
    
    @Data
    public static class Job {
        private Interval interval = new Interval();
        
        @Data
        public static class Interval {
            private int minutes = 60;
        }
    }
    
    @Data
    public static class Incremental {
        private boolean enabled = true;
    }
}