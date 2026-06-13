package customer_issue_resolution.strategy;

import customer_issue_resolution.entities.Agent;
import customer_issue_resolution.entities.Issue;

import java.util.List;

public interface AssignmentStrategy {
    Agent assignIssue(Issue issue, List<Agent> agents);
}
