package com.devsync.devsync_server.github.service;

import com.devsync.devsync_server.github.entity.GitHubEvent;
import com.devsync.devsync_server.github.repository.GitHubEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GitHubEventService {

    private final GitHubEventRepository gitHubEventRepository;

    public List<GitHubEvent> getAllEvents() {

        return gitHubEventRepository.findAll();
    }

    public List<GitHubEvent> getRecentEvents() {

        return gitHubEventRepository
                .findTop20ByOrderByCreatedAtDesc();
    }

    public List<GitHubEvent> getEventsByType(
            String eventType
    ) {

        return gitHubEventRepository
                .findByEventTypeOrderByCreatedAtDesc(
                        eventType
                );
    }
}