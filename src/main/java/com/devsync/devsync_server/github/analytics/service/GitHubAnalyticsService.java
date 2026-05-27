package com.devsync.devsync_server.github.analytics.service;

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
}