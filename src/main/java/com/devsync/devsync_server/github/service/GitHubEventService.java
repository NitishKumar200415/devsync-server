package com.devsync.devsync_server.github.service;

import com.devsync.devsync_server.github.entity.GitHubEvent;
import com.devsync.devsync_server.github.repository.GitHubEventRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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

    public List<GitHubEvent> getPaginatedEvents(
            int page,
            int size
    ) {

        Pageable pageable =
                PageRequest.of(page, size);

        return gitHubEventRepository
                .findAll(pageable)
                .getContent();
    }

    public List<GitHubEvent> searchEvents(
            String type,
            String actor
    ) {

        if (type != null && actor != null) {

            return gitHubEventRepository
                    .findByEventTypeAndActor(
                            type,
                            actor
                    );
        }

        if (type != null) {

            return gitHubEventRepository
                    .findByEventTypeOrderByCreatedAtDesc(
                            type
                    );
        }

        if (actor != null) {

            return gitHubEventRepository
                    .findByActor(actor);
        }

        return gitHubEventRepository.findAll();
    }

    public List<GitHubEvent> searchEventsPaged(
            String type,
            int page,
            int size,
            String direction
    ) {

        Sort sort =
                direction.equalsIgnoreCase("desc")
                        ? Sort.by("createdAt").descending()
                        : Sort.by("createdAt").ascending();

        Pageable pageable =
                PageRequest.of(page, size, sort);

        if (type != null) {

            return gitHubEventRepository
                    .findByEventType(
                            type,
                            pageable
                    )
                    .getContent();
        }

        return gitHubEventRepository
                .findAll(pageable)
                .getContent();
    }
}