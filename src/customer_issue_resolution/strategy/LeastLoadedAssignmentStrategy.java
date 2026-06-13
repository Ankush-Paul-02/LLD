package customer_issue_resolution.strategy;

import customer_issue_resolution.entities.Agent;
import customer_issue_resolution.entities.Issue;
import customer_issue_resolution.enums.IssueType;

import java.util.Comparator;
import java.util.List;

public class LeastLoadedAssignmentStrategy implements AssignmentStrategy {

    @Override
    public Agent assignIssue(Issue issue, List<Agent> agents) {
        IssueType issueType = issue.getIssueType();
        if (issueType == null) {
            throw new IllegalStateException("Issue type is null");
        }

        return agents.stream()
                .filter(agent -> agent.getExpertise().contains(issueType))
                .min(Comparator.comparingInt(Agent::getCurrentWorkload))
                .orElseThrow(
                        () -> new IllegalStateException("Agent with type " + issueType + " not found")
                );
    }
}
