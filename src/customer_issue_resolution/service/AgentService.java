package customer_issue_resolution.service;

import customer_issue_resolution.entities.Agent;
import customer_issue_resolution.enums.IssueType;
import customer_issue_resolution.repository.AgentRepository;

import java.util.Set;
import java.util.UUID;

public class AgentService {
    private final AgentRepository agentRepository;

    public AgentService(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    public Agent addAgent(String name, String email, Set<IssueType> expertise) {
        Agent agent = agentRepository.findByEmail(email);
        if (agent != null) {
            System.out.println("Agent already exists: " + email);
            throw new IllegalArgumentException("Agent already exists: " + email);
        }

        agent = new Agent(
                UUID.randomUUID().toString(),
                name,
                email,
                expertise
        );
        return agentRepository.save(agent);
    }
}
