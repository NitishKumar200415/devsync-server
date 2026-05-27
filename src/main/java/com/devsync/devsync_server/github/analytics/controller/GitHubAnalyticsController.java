package com.devsync.devsync_server.github.analytics.controller;

import com.devsync.devsync_server.github.analytics.dto.GitHubAnalyticsStatsDTO;
import com.devsync.devsync_server.github.analytics.service.GitHubAnalyticsService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/github/analytics")
@RequiredArgsConstructor
public class GitHubAnalyticsController {

    private final GitHubAnalyticsService gitHubAnalyticsService;

    @GetMapping("/stats")
    public ResponseEntity<GitHubAnalyticsStatsDTO> getStats() {

        GitHubAnalyticsStatsDTO stats =
                gitHubAnalyticsService.getStats();

        return ResponseEntity.ok(stats);
    }
}