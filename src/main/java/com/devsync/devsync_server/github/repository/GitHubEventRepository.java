package com.devsync.devsync_server.github.repository;

import com.devsync.devsync_server.github.entity.GitHubEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface GitHubEventRepository
        extends JpaRepository<GitHubEvent, Long> {

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
            String eventType,
            Pageable pageable
    );
}