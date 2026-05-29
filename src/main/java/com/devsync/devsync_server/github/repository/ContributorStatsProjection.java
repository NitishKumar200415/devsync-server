package com.devsync.devsync_server.github.analytics.repository;

public interface ContributorStatsProjection {

    String getActor();

    Long getEventCount();
}