package com.devsync.devsync_server.github.controller;

import com.devsync.devsync_server.github.dto.GitHubEventResponseDTO;
import com.devsync.devsync_server.github.service.GitHubEventService;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

import lombok.RequiredArgsConstructor;

import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/github/events")
@Validated
public class GitHubEventController {

    private final GitHubEventService gitHubEventService;

    @GetMapping
    public List<GitHubEventResponseDTO> getAllEvents() {

        return gitHubEventService.getAllEvents();
    }

    @GetMapping("/recent")
    public List<GitHubEventResponseDTO> getRecentEvents() {

        return gitHubEventService.getRecentEvents();
    }

    @GetMapping("/type/{type}")
    public List<GitHubEventResponseDTO> getEventsByType(
            @PathVariable String type
    ) {

        return gitHubEventService
                .getEventsByType(type);
    }

    @GetMapping("/paged")
    public List<GitHubEventResponseDTO> getPaginatedEvents(

            @RequestParam
            @Min(0)
            int page,

            @RequestParam
            @Min(1)
            int size
    ) {

        return gitHubEventService
                .getPaginatedEvents(
                        page,
                        size
                );
    }

    @GetMapping("/search")
    public List<GitHubEventResponseDTO> searchEvents(

            @RequestParam(required = false)
            String type,

            @RequestParam(required = false)
            String actor
    ) {

        return gitHubEventService
                .searchEvents(
                        type,
                        actor
                );
    }

    @GetMapping("/search/paged")
    public List<GitHubEventResponseDTO> searchEventsPaged(

            @RequestParam(required = false)
            String type,

            @RequestParam
            @Min(0)
            int page,

            @RequestParam
            @Min(1)
            int size,

            @RequestParam(defaultValue = "desc")
            @Pattern(
                    regexp = "asc|desc",
                    message = "Direction must be asc or desc"
            )
            String direction
    ) {

        return gitHubEventService
                .searchEventsPaged(
                        type,
                        page,
                        size,
                        direction
                );
    }
}