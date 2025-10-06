package com.apache.apache.service;

import com.apache.apache.config.MongoConfig;
import com.apache.apache.domain.JobExecutionRecord;
import lombok.RequiredArgsConstructor;
import org.apache.spark.sql.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TicketAnalysisService {

    private final SparkSession sparkSession;
    private final MongoConfig mongoConfig;

    /**
     * Runs the main ticket analysis job.
     */
    public JobExecutionRecord processTicketData() {
        LocalDateTime startTime = LocalDateTime.now();
        long startMillis = System.currentTimeMillis();

        try {
            System.out.println("🚀 Starting ticket analysis job...");

            // ✅ Load data from MongoDB
            Dataset<Row> tickets = sparkSession.read()
                    .format("mongodb")
                    .option("uri", mongoConfig.getFullUri())
                    .option("collection", mongoConfig.getTicketsCollectionPath())
                    .load();

            tickets.createOrReplaceTempView("tickets");

            // ✅ Example analysis: count tickets per category & priority
            Dataset<Row> results = sparkSession.sql(
                    "SELECT category, priority, COUNT(*) AS total_tickets " +
                    "FROM tickets " +
                    "GROUP BY category, priority"
            );

            // ✅ Save analysis results to MongoDB
            results.write()
                    .format("mongodb")
                    .option("uri", mongoConfig.getFullUri())
                    .option("collection", mongoConfig.getAnalyticsCollectionPath())
                    .mode(SaveMode.Overwrite)
                    .save();

            long duration = System.currentTimeMillis() - startMillis;
            System.out.println("✅ Ticket analysis job completed successfully!");

            return new JobExecutionRecord(
                    "TicketAnalysisJob",
                    startTime,
                    true,
                    duration,
                    null,
                    results.count()
            );

        } catch (Exception e) {
            long duration = System.currentTimeMillis() - startMillis;
            e.printStackTrace();

            return new JobExecutionRecord(
                    "TicketAnalysisJob",
                    startTime,
                    false,
                    duration,
                    e.getMessage(),
                    0
            );
        }
    }
}
