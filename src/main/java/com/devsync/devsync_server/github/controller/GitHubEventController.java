package com.devsync.devsync_server.github.controller;

import com.devsync.devsync_server.github.entity.GitHubEvent;
import com.devsync.devsync_server.github.service.GitHubEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/github/events")
public class GitHubEventController {

    private final GitHubEventService gitHubEventService;

    @GetMapping
    public List<GitHubEvent> getAllEvents() {

        return gitHubEventService.getAllEvents();
    }

    @GetMapping("/recent")
    public List<GitHubEvent> getRecentEvents() {

        return gitHubEventService.getRecentEvents();
    }

    @GetMapping("/type/{type}")
    public List<GitHubEvent> getEventsByType(
            @PathVariable String type
    ) {

        return gitHubEventService
                .getEventsByType(type);
    }
    @GetMapping("/paged")
    public List<GitHubEvent> getPaginatedEvents(
            @RequestParam int page,
            @RequestParam int size
    ) {

        return gitHubEventService
                .getPaginatedEvents(page, size);
    }
    @GetMapping("/search")
    public List<GitHubEvent> searchEvents(

            @RequestParam(required = false)
            String type,

            @RequestParam(required = false)
            String actor
    ) {

        return gitHubEventService
                .searchEvents(type, actor);
    }
}