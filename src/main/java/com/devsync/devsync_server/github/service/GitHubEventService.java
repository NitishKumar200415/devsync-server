package com.devsync.devsync_server.github.service;

import com.devsync.devsync_server.github.dto.GitHubEventResponseDTO;
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

    public List<GitHubEventResponseDTO> getAllEvents() {

        return gitHubEventRepository
                .findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    public List<GitHubEventResponseDTO> getRecentEvents() {

        return gitHubEventRepository
                .findTop20ByOrderByCreatedAtDesc()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    public List<GitHubEventResponseDTO> getEventsByType(
            String eventType
    ) {

        return gitHubEventRepository
                .findByEventTypeOrderByCreatedAtDesc(
                        eventType
                )
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    public List<GitHubEventResponseDTO> getPaginatedEvents(
            int page,
            int size
    ) {

        Pageable pageable =
                PageRequest.of(page, size);

        return gitHubEventRepository
                .findAll(pageable)
                .getContent()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    public List<GitHubEventResponseDTO> searchEvents(
            String type,
            String actor
    ) {

        if (type != null && actor != null) {

            return gitHubEventRepository
                    .findByEventTypeAndActor(
                            type,
                            actor
                    )
                    .stream()
                    .map(this::mapToDTO)
                    .toList();
        }

        if (type != null) {

            return gitHubEventRepository
                    .findByEventTypeOrderByCreatedAtDesc(
                            type
                    )
                    .stream()
                    .map(this::mapToDTO)
                    .toList();
        }

        if (actor != null) {

            return gitHubEventRepository
                    .findByActor(actor)
                    .stream()
                    .map(this::mapToDTO)
                    .toList();
        }

        return gitHubEventRepository
                .findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    public List<GitHubEventResponseDTO> searchEventsPaged(
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
                    .getContent()
                    .stream()
                    .map(this::mapToDTO)
                    .toList();
        }

        return gitHubEventRepository
                .findAll(pageable)
                .getContent()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    private GitHubEventResponseDTO mapToDTO(
            GitHubEvent event
    ) {

        return new GitHubEventResponseDTO(
                event.getId(),
                event.getEventType(),
                event.getRepositoryName(),
                event.getActor(),
                event.getBranchName(),
                event.getCreatedAt()
        );
    }
}