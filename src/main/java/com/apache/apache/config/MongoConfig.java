package com.apache.apache.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class MongoConfig {
    
    @Value("${mongodb.atlas.uri}")
    private String mongoUri;
    
    @Value("${mongodb.atlas.database}")
    private String database;
    
    @Value("${mongodb.atlas.collection.tickets}")
    private String ticketsCollection;
    
    @Value("${mongodb.atlas.collection.analytics}")
    private String analyticsCollection;
    
    public String getFullUri() {
        return mongoUri;
    }
    
    public String getTicketsCollectionPath() {
        return database + "." + ticketsCollection;
    }
    
    public String getAnalyticsCollectionPath() {
        return database + "." + analyticsCollection;
    }
}