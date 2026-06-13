package customer_issue_resolution.entities;

import customer_issue_resolution.enums.IssueStatus;
import customer_issue_resolution.enums.IssueType;

import java.time.LocalDateTime;

public class Issue {
    private final String id;
    private String transactionId;
    private IssueType issueType;
    private IssueStatus issueStatus;
    private String subject;
    private String description;
    private String customerEmail;
    private String resolution;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String agentId;

    public Issue(String id, String transactionId, IssueType issueType, IssueStatus issueStatus, String subject, String description, String customerEmail, LocalDateTime createdAt) {
        this.id = id;
        this.transactionId = transactionId;
        this.issueType = issueType;
        this.issueStatus = issueStatus;
        this.subject = subject;
        this.description = description;
        this.customerEmail = customerEmail;
        this.createdAt = createdAt;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getAgentId() {
        return agentId;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public IssueStatus getIssueStatus() {
        return issueStatus;
    }

    public void setIssueStatus(IssueStatus issueStatus) {
        this.issueStatus = issueStatus;
    }

    public String getId() {
        return id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public IssueType getIssueType() {
        return issueType;
    }

    public void setIssueType(IssueType issueType) {
        this.issueType = issueType;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
