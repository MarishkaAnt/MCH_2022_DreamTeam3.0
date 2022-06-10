package com.dreamteam3.data.repository;

import com.dreamteam3.data.model.WebPage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebPageRepository extends JpaRepository<WebPage, Long> {
}
