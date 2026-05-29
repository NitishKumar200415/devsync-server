package com.devsync.devsync_server.github.analytics.service;

import java.util.ArrayList;
import java.util.List;

import com.devsync.devsync_server.github.analytics.dto.ContributorLeaderboardDTO;
import com.devsync.devsync_server.github.analytics.repository.ContributorStatsProjection;
import com.devsync.devsync_server.github.analytics.dto.GitHubAnalyticsStatsDTO;
import com.devsync.devsync_server.github.repository.GitHubEventRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GitHubAnalyticsService {

    private final GitHubEventRepository gitHubEventRepository;

    public GitHubAnalyticsStatsDTO getStats() {

        long totalEvents =
                gitHubEventRepository.count();

        long pushEvents =
                gitHubEventRepository.countByEventType(
                        "push"
                );

        long pullRequestEvents =
                gitHubEventRepository.countByEventType(
                        "pull_request"
                );

        long issueEvents =
                gitHubEventRepository.countByEventType(
                        "issues"
                );

        return new GitHubAnalyticsStatsDTO(
                totalEvents,
                pushEvents,
                pullRequestEvents,
                issueEvents
        );
    }
    public List<ContributorLeaderboardDTO> getContributorLeaderboard() {

        List<ContributorStatsProjection> stats =
                gitHubEventRepository.getContributorLeaderboard();

        List<ContributorLeaderboardDTO> leaderboard =
                new ArrayList<>();

        int rank = 1;

        for (ContributorStatsProjection contributor : stats) {

            leaderboard.add(
                    new ContributorLeaderboardDTO(
                            rank++,
                            contributor.getActor(),
                            contributor.getEventCount()
                    )
            );
        }

        return leaderboard;
    }
}