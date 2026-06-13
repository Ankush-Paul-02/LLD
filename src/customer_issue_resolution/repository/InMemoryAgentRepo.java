package customer_issue_resolution.repository;

import customer_issue_resolution.entities.Agent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryAgentRepo implements AgentRepository {

    private final Map<String, Agent> agents = new ConcurrentHashMap<>();

    @Override
    public Agent save(Agent agent) {
        agents.put(agent.getId(), agent);
        return agent;
    }

    @Override
    public Agent findById(String id) {
        return agents.get(id);
    }

    @Override
    public Agent findByEmail(String email) {
        return agents
                .values()
                .stream()
                .filter(
                        agent -> agent.getEmail().equals(email)
                )
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Agent> findAll() {
        return new ArrayList<>(agents.values());
    }

    @Override
    public Agent findByIssueId(String issueId) {
        return agents.values().stream().filter(agent -> agent.getAssignedIssues().contains(issueId)).findFirst().orElse(null);
    }
}
