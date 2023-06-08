package com.example.filehandlingdemo.apikey.persistence.Repository;

import com.example.filehandlingdemo.apikey.persistence.Models.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApiKeyRepo extends JpaRepository<ApiKey, Long> {
    Optional<ApiKey> findByRefNo(String refNo);

    boolean existsByApiKeyAndIsExpiredFalse(String apiKey);
}
