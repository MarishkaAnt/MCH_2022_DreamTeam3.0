package com.dreamteam3.rest.controller;

import com.dreamteam3.data.model.WebPage;
import com.dreamteam3.data.service.WebPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/page")
@RequiredArgsConstructor
public class WebPageController {
    //TODO набросок контроллера, нужно продумать все методы и реализовать
    private final WebPageService webPageService;

    @GetMapping
    public List<WebPage> getAll() {
        return webPageService.findAll();
    }
}
