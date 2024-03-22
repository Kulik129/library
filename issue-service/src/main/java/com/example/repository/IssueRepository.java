package com.example.repository;

import com.example.models.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IssueRepository extends JpaRepository<Issue, Long> {
    Optional<Issue> findByUuid(UUID uuid);
}
