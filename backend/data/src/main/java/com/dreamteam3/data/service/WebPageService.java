package com.dreamteam3.data.service;

import com.dreamteam3.data.model.WebPage;

import java.util.List;

public interface WebPageService {
    //TODO набросок сервиса, нужно продумать все методы и реализовать
    List<WebPage> findAll();
    WebPage save(WebPage page);
}
