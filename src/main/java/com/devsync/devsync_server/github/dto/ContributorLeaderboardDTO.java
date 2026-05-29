package com.devsync.devsync_server.github.analytics.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ContributorLeaderboardDTO {

    private Integer rank;

    private String actor;

    private Long eventCount;
}