package customer_issue_resolution.repository;

import customer_issue_resolution.entities.Agent;

import java.util.List;

public interface AgentRepository {

    Agent save(Agent agent);

    Agent findById(String id);

    Agent findByEmail(String email);

    List<Agent> findAll();

    Agent findByIssueId(String issueId);
}