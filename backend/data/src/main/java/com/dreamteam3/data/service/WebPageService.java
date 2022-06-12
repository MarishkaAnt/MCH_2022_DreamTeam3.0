package com.dreamteam3.data.service;

import com.dreamteam3.data.model.WebPage;
import com.dreamteam3.data.repository.WebPageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WebPageService {

    private final WebPageRepository webPageRepository;

    public WebPage save(WebPage webPage) {
        return webPageRepository.saveAndFlush(webPage);
    }

    public List<WebPage> saveAll(List<WebPage> webPages) {
        return webPageRepository.saveAllAndFlush(webPages);
    }

    public void delete(Long id) {
        WebPage webPage = webPageRepository.findById(id).orElseThrow(NullPointerException::new);
        webPageRepository.deleteById(id);
    }

    public List<WebPage> findAll() {
        return new ArrayList<WebPage>(webPageRepository.findAll());
    }

    public List<WebPage> findAllByTitle(String text, int pageNumber, int size) {
        Pageable page = PageRequest.of(pageNumber, size);
        return webPageRepository.findAllByTitleContainsIgnoreCase(text, page);
    }

}
