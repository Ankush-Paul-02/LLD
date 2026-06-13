package customer_issue_resolution.entities;

import customer_issue_resolution.enums.IssueType;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Agent {

    private final String id;
    private String name;
    private String email;
    private Set<IssueType> expertise;

    // Thread-safe set
    private final Set<String> assignedIssues;

    public Agent(String id,
                 String name,
                 String email,
                 Set<IssueType> expertise) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.expertise = expertise;
        this.assignedIssues = ConcurrentHashMap.newKeySet();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public Set<IssueType> getExpertise() {
        return expertise;
    }

    public void setExpertise(Set<IssueType> expertise) {
        this.expertise = expertise;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Domain methods
    public void assignIssue(String issueId) {
        assignedIssues.add(issueId);
    }

    public void removeIssue(String issueId) {
        assignedIssues.remove(issueId);
    }

    public int getCurrentWorkload() {
        return assignedIssues.size();
    }

    // Read-only view
    public Set<String> getAssignedIssues() {
        return Collections.unmodifiableSet(assignedIssues);
    }
}