package customer_issue_resolution;

import customer_issue_resolution.entities.Agent;
import customer_issue_resolution.entities.Issue;
import customer_issue_resolution.enums.IssueType;
import customer_issue_resolution.repository.AgentRepository;
import customer_issue_resolution.repository.InMemoryAgentRepo;
import customer_issue_resolution.repository.InMemoryIssueRepo;
import customer_issue_resolution.repository.IssueRepository;
import customer_issue_resolution.service.AgentService;
import customer_issue_resolution.service.IssueService;
import customer_issue_resolution.strategy.AssignmentStrategy;
import customer_issue_resolution.strategy.LeastLoadedAssignmentStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DemoApp {

    public static void main(String[] args) throws InterruptedException {

        AgentRepository agentRepository = new InMemoryAgentRepo();
        IssueRepository issueRepository = new InMemoryIssueRepo();

        AssignmentStrategy strategy = new LeastLoadedAssignmentStrategy();

        AgentService agentService =
                new AgentService(agentRepository);

        IssueService issueService =
                new IssueService(
                        issueRepository,
                        agentRepository,
                        strategy
                );

        // Agents
        agentService.addAgent(
                "John",
                "john@test.com",
                Set.of(IssueType.PAYMENT, IssueType.GOLD)
        );

        agentService.addAgent(
                "Alice",
                "alice@test.com",
                Set.of(IssueType.PAYMENT, IssueType.INSURANCE)
        );

        agentService.addAgent(
                "Bob",
                "bob@test.com",
                Set.of(
                        IssueType.PAYMENT,
                        IssueType.GOLD,
                        IssueType.INSURANCE,
                        IssueType.MUTUAL_FUND
                )
        );

        // Create Issues
        List<Issue> issues = new ArrayList<>();

        for (int i = 1; i <= 50; i++) {

            IssueType issueType;

            switch (i % 4) {
                case 0 -> issueType = IssueType.PAYMENT;
                case 1 -> issueType = IssueType.GOLD;
                case 2 -> issueType = IssueType.INSURANCE;
                default -> issueType = IssueType.MUTUAL_FUND;
            }

            Issue issue = issueService.createIssue(
                    "TXN-" + i,
                    "Subject-" + i,
                    "Description-" + i,
                    "customer" + i + "@test.com",
                    issueType
            );

            issues.add(issue);
        }

        System.out.println("\n=== Concurrent Assignment Test ===\n");

        int threadCount = 10;

        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        CountDownLatch latch = new CountDownLatch(issues.size());

        for (Issue issue : issues) {

            executor.submit(() -> {
                try {
                    issueService.assignIssue(issue.getId());
                } catch (Exception ex) {
                    System.out.println(
                            "Assignment failed for issue "
                                    + issue.getId()
                                    + " : "
                                    + ex.getMessage()
                    );
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executor.shutdown();

        System.out.println("\n=== Agent Workloads ===\n");

        for (Agent agent : agentRepository.findAll()) {
            System.out.println(
                    agent.getName()
                            + " -> "
                            + agent.getCurrentWorkload()
                            + " issues"
            );
        }

        System.out.println("\n=== Sample Resolution Test ===\n");

        Issue firstIssue = issues.getFirst();

        issueService.resolveIssue(
                firstIssue.getId(),
                "Issue resolved successfully"
        );

        for (Agent agent : agentRepository.findAll()) {
            System.out.println(
                    agent.getName()
                            + " -> "
                            + agent.getCurrentWorkload()
                            + " issues"
            );
        }
    }
}