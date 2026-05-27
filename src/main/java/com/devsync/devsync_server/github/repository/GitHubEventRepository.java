package com.devsync.devsync_server.github.repository;

import com.devsync.devsync_server.github.entity.GitHubEvent;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface GitHubEventRepository
        extends JpaRepository<GitHubEvent, Long>,
        JpaSpecificationExecutor<GitHubEvent> {

    List<GitHubEvent>
    findTop20ByOrderByCreatedAtDesc();

    List<GitHubEvent>
    findByEventTypeOrderByCreatedAtDesc(
            String eventType
    );

    List<GitHubEvent>
    findByEventTypeAndActor(
            String eventType,
            String actor
    );

    List<GitHubEvent>
    findByActor(
            String actor
    );

    Page<GitHubEvent>
    findByEventType(
            String type,
            Pageable pageable
    );

    long countByEventType(
            String eventType
    );
}