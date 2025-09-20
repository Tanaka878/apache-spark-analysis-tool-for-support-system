package com.apache.apache.config;

import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SparkConfig {

    @Value("${spark.app.name}")
    private String appName;

    @Value("${spark.master}")
    private String masterUrl;

    @Value("${spark.executor.memory}")
    private String executorMemory;

    @Value("${spark.driver.memory}")
    private String driverMemory;

    @Value("${mongodb.atlas.uri}")
    private String mongoUri;

    @Bean
    public SparkSession sparkSession() {
        return SparkSession.builder()
                .appName(appName)
                .master(masterUrl)
                .config("spark.executor.memory", executorMemory)
                .config("spark.driver.memory", driverMemory)
                .config("spark.sql.adaptive.enabled", "true")
                .config("spark.sql.adaptive.coalescePartitions.enabled", "true")
                .config("spark.mongodb.read.connection.uri", mongoUri)
                .config("spark.mongodb.write.connection.uri", mongoUri)
                .config("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
                .config("spark.ui.enabled", "false")  // 🚀 Disable UI
                .getOrCreate();
    }

    @Bean
    public SparkSession streamingSparkSession() {
        return SparkSession.builder()
                .appName(appName + "-Streaming")
                .master(masterUrl)
                .config("spark.executor.memory", executorMemory)
                .config("spark.driver.memory", driverMemory)
                .config("spark.sql.streaming.checkpointLocation", "/tmp/spark-checkpoint")
                .config("spark.mongodb.read.connection.uri", mongoUri)
                .config("spark.ui.enabled", "false")  // 🚀 Disable UI
                .config("spark.mongodb.write.connection.uri", mongoUri)
                .getOrCreate();
    }
}