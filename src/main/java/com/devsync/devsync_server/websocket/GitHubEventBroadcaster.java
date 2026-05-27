package com.devsync.devsync_server.github.websocket;

import com.devsync.devsync_server.github.dto.GitHubEventResponseDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GitHubEventBroadcaster {

    private final SimpMessagingTemplate messagingTemplate;

    public void broadcastEvent(
            GitHubEventResponseDTO event
    ) {

        log.info(
                "Broadcasting GitHub event: {}",
                event.getEventType()
        );

        // Main global activity stream
        messagingTemplate.convertAndSend(
                "/topic/github-events",
                event
        );

        // Event-type-specific streams
        messagingTemplate.convertAndSend(
                "/topic/github/" + event.getEventType(),
                event
        );
    }
}