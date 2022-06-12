package com.dreamteam3.crawler;

import com.dreamteam3.crawler.factory.PageCrawlerFactory;

import com.dreamteam3.crawler.service.PageCrawler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootApplication(scanBasePackages = {
        "com.dreamteam3.data",
        "com.dreamteam3.crawler"
})
@EnableJpaRepositories(basePackages = {"com.dreamteam3.data.repository"})
@EntityScan(basePackages = {"com.dreamteam3.data.model"})
@RequiredArgsConstructor
public class CrawlerUtility implements CommandLineRunner {

    private final PageCrawlerFactory pageCrawlerFactory;

    @Value("${com.dreamteam3.mosprom.crawler.parsers}")
    private int parsers;

    public static void main(String[] args) {
        SpringApplication.run(CrawlerUtility.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(parsers);
        for (int i = 0; i < parsers; i++) {
            PageCrawler pageCrawler = pageCrawlerFactory.getPageCrawler();
            executorService.submit(pageCrawler);
        }

        boolean isFinished = executorService.awaitTermination(1, TimeUnit.MINUTES);
        if (!isFinished) {
            executorService.shutdown();
        }
    }
}
