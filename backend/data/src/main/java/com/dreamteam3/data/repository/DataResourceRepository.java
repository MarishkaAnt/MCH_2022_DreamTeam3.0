package com.dreamteam3.data.repository;

import com.dreamteam3.data.model.DataResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataResourceRepository extends JpaRepository<DataResource, Long> {
}
