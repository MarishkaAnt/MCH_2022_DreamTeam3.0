package com.dreamteam3.crawler.factory;

import com.dreamteam3.crawler.service.PageCrawler;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public abstract class PageCrawlerFactory {

    @Lookup
    public abstract PageCrawler getPageCrawler();
}
