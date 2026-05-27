package com.devsync.devsync_server.github.specification;

import com.devsync.devsync_server.github.entity.GitHubEvent;

import org.springframework.data.jpa.domain.Specification;

public class GitHubEventSpecification {

    public static Specification<GitHubEvent>
    hasType(String type) {

        return (root, query, criteriaBuilder) ->

                type == null
                        ? null
                        : criteriaBuilder.equal(
                        root.get("eventType"),
                        type
                );
    }

    public static Specification<GitHubEvent>
    hasActor(String actor) {

        return (root, query, criteriaBuilder) ->

                actor == null
                        ? null
                        : criteriaBuilder.equal(
                        root.get("actor"),
                        actor
                );
    }

    public static Specification<GitHubEvent>
    hasRepository(String repository) {

        return (root, query, criteriaBuilder) ->

                repository == null
                        ? null
                        : criteriaBuilder.equal(
                        root.get("repositoryName"),
                        repository
                );
    }
}