package com.devsync.devsync_server.github.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GitHubEventResponseDTO {

    private Long id;

    private String eventType;

    private String repositoryName;

    private String actor;

    private String branchName;

    private LocalDateTime createdAt;
}