package com.dreamteam3.data.repository;

import com.dreamteam3.data.model.WebPage;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WebPageRepository extends JpaRepository<WebPage, Long> {

    List<WebPage> findAllByTitleContainsIgnoreCase(String title, Pageable pageable);

}
