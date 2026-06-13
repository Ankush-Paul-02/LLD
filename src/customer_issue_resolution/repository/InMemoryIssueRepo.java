package customer_issue_resolution.repository;

import customer_issue_resolution.entities.Issue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryIssueRepo implements IssueRepository {

    private final Map<String, Issue> issues = new ConcurrentHashMap<>();

    @Override
    public Issue save(Issue issue) {
        issues.put(issue.getId(), issue);
        return issue;
    }

    @Override
    public Issue findById(String id) {
        return issues.get(id);
    }

    @Override
    public List<Issue> findAll() {
        return new ArrayList<>(issues.values());
    }
}
