package com.devsync.devsync_server.github.repository;

import com.devsync.devsync_server.github.entity.GitHubEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}