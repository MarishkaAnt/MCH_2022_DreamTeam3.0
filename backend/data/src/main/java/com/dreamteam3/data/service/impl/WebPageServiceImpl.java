package com.dreamteam3.data.service.impl;

import com.dreamteam3.data.model.WebPage;
import com.dreamteam3.data.repository.WebPageRepository;
import com.dreamteam3.data.service.WebPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WebPageServiceImpl implements WebPageService {

    private final WebPageRepository webPageRepository;

    @Override
    public List<WebPage> findAll() {
        return webPageRepository.findAll();
    }

    @Override
    public WebPage save(WebPage page) {
        return webPageRepository.saveAndFlush(page);
    }
}
