package com.apache.apache.domain;

import com.apache.apache.dto.Category;
import com.apache.apache.dto.Priority;
import com.apache.apache.dto.Status;
import com.apache.apache.dto.TicketSource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketAnalysis implements Serializable {
    
    private String ticketId;
    private String question;
    private String issuerEmail;
    private List<String> replies = new ArrayList<>();
    
    // Temporal fields
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime resolvedAt;
    private Long resolutionTimeMinutes;
    
    // Classification fields
    private Status status;
    private Priority priority;
    private Category category;
    private TicketSource source;
    
    // Conversation metadata
    private Integer totalReplies;
    private LocalDateTime lastReplyAt;
    private Boolean requiresFollowup;
    
    public void addReply(String reply) {
        if (this.replies == null) {
            this.replies = new ArrayList<>();
        }
        this.replies.add(reply);
        this.totalReplies = this.replies.size();
        this.lastReplyAt = LocalDateTime.now();
    }
}