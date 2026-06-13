package customer_issue_resolution.repository;

import customer_issue_resolution.entities.Issue;

import java.util.List;

public interface IssueRepository {

    Issue save(Issue issue);

    Issue findById(String id);

    List<Issue> findAll();
}