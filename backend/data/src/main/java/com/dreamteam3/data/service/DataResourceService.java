package com.dreamteam3.data.service;

import com.dreamteam3.data.model.DataResource;
import com.dreamteam3.data.repository.DataResourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DataResourceService {

    private final DataResourceRepository dataResourceRepository;

    public List<DataResource> findAll() {
        return dataResourceRepository.findAll();
    }

}
