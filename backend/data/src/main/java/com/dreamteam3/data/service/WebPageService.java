package com.dreamteam3.data.service;

import com.dreamteam3.data.model.WebPage;
import com.dreamteam3.data.repository.WebPageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WebPageService {

    @Autowired
    WebPageRepository webPageRepository;

    public void createWebPage(WebPage webPage) {
        WebPage newWebPage = new WebPage(); 

        newWebPage.setUrl(webPage.getUrl());

        webPageRepository.save(webPage);
    }

    public void deleteWebPage(Long id) {
        WebPage webPage = webPageRepository.findById(id).orElseThrow(NullPointerException::new);
        webPageRepository.deleteById(id);
    }

    public List<WebPage> findAll() {
        return new ArrayList<WebPage>(webPageRepository.findAll());
    }

    public void save(WebPage webPage) {
        webPageRepository.save(webPage);
    }
}
