package customer_issue_resolution.service;

import customer_issue_resolution.entities.Agent;
import customer_issue_resolution.entities.Issue;
import customer_issue_resolution.enums.IssueStatus;
import customer_issue_resolution.enums.IssueType;
import customer_issue_resolution.repository.AgentRepository;
import customer_issue_resolution.repository.IssueRepository;
import customer_issue_resolution.strategy.AssignmentStrategy;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class IssueService {
    private final IssueRepository issueRepository;
    private final AgentRepository agentRepository;
    private final AssignmentStrategy assignmentStrategy;

    public IssueService(IssueRepository issueRepository, AgentRepository agentRepository, AssignmentStrategy assignmentStrategy) {
        this.issueRepository = issueRepository;
        this.agentRepository = agentRepository;
        this.assignmentStrategy = assignmentStrategy;
    }

    public Issue createIssue(
            String transactionId,
            String subject,
            String desc,
            String customerEmail,
            IssueType issueType
    ) {
        Issue newIssue = new Issue(
                UUID.randomUUID().toString(),
                transactionId,
                issueType,
                IssueStatus.INITIATED,
                subject,
                desc,
                customerEmail,
                LocalDateTime.now()
        );

        return issueRepository.save(newIssue);
    }

    public Agent assignIssue(String issueId) {
        Issue issue = issueRepository.findById(issueId);
        if (issue == null) {
            System.out.println("Invalid issue id: " + issueId);
            throw new IllegalArgumentException("Invalid issue id: " + issueId);
        }

        List<Agent> agents = agentRepository.findAll();

        Agent agent = assignmentStrategy.assignIssue(issue, agents);
        if (agent == null) {
            System.out.println("Invalid agent id: " + issueId);
            throw new IllegalArgumentException("Invalid agent id: " + issueId);
        }
        agent.assignIssue(issueId);
        agentRepository.save(agent);
        issue.setIssueStatus(IssueStatus.ALLOCATED);
        issue.setUpdatedAt(LocalDateTime.now());
        issue.setAgentId(agent.getId());
        issueRepository.save(issue);

        System.out.println("Assigned agent: " + agent);
        return agent;
    }

    public Issue findIssue(String issueId) {
        Issue issue = issueRepository.findById(issueId);
        if (issue == null) {
            System.out.println("Invalid issue id: " + issueId);
            throw new IllegalArgumentException("Invalid issue id: " + issueId);
        }

        return issue;
    }

    public Issue updateIssue(String issueId, IssueStatus issueStatus, String resolution) {
        Issue issue = issueRepository.findById(issueId);
        if (issue == null) {
            System.out.println("Invalid issue id: " + issueId);
            throw new IllegalArgumentException("Invalid issue id: " + issueId);
        }

        issue.setIssueStatus(issueStatus);
        issue.setResolution(resolution);
        issue.setUpdatedAt(LocalDateTime.now());
        System.out.println("Updated issue: " + issue);
        return issueRepository.save(issue);
    }

    public Issue resolveIssue(String issueId, String resolution) {
        Issue issue = issueRepository.findById(issueId);
        if (issue == null) {
            System.out.println("Invalid issue id: " + issueId);
            throw new IllegalArgumentException("Invalid issue id: " + issueId);
        }
        issue.setIssueStatus(IssueStatus.RESOLVED);
        issue.setResolution(resolution);
        issue.setUpdatedAt(LocalDateTime.now());
        System.out.println("Resolved issue: " + issue);
        String agentId = issue.getAgentId();
        Agent agent = agentRepository.findById(agentId);
        if (agent == null) {
            System.out.println("Invalid agent id: " + issueId);
            throw new IllegalArgumentException("Invalid agent id: " + issueId);
        }
        agent.removeIssue(issue.getId());
        agentRepository.save(agent);
        return issueRepository.save(issue);
    }
}
