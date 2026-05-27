package com.devsync.devsync_server.github.analytics.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GitHubAnalyticsStatsDTO {

    private long totalEvents;

    private long pushEvents;

    private long pullRequestEvents;

    private long issueEvents;
}