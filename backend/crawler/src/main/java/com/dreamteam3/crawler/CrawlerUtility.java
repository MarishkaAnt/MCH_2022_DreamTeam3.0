package com.dreamteam3.crawler;

import com.dreamteam3.crawler.factory.PageCrawlerFactory;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
        "com.dreamteam3.data",
        "com.dreamteam3.crawler"
})
@EnableJpaRepositories(basePackages = {"com.dreamteam3.data.repository"})
@EntityScan(basePackages = {"com.dreamteam3.data.model"})
@RequiredArgsConstructor
public class CrawlerUtility implements CommandLineRunner {

    private final PageCrawlerFactory pageCrawlerFactory;

    @Value("${com.dreamteam3.producing.crawler.parsers}")
    private int parsers;

    public static void main(String[] args) {
        SpringApplication.run(CrawlerUtility.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String crawlStorageFolder = "/data/crawl/root";
        int numberOfCrawlers = 1;

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);

        // Instantiate the controller for this crawl.
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        //TODO получить порцию компаний из БД (предлагаю хранить размер порции в пропертях)
        // грузить все сразу не получится, т.к. может быть очень много записей в таблице компаний
        // далее запускаем блокирующую очередь, в нее добавляем ссылки для парсинга и запускаем несколько парсеров
        // количество парсеров тоже хранится в пропертях, они разгребают очередь, и парсят ссылки

        // For each crawl, you need to add some seed urls. These are the first
        // URLs that are fetched and then the crawler starts following links
        // which are found in these pages
        controller.addSeed("http://www.udarnitsa.ru/");

        // The factory which creates instances of crawlers.
        CrawlController.WebCrawlerFactory<WebCrawler> factory = pageCrawlerFactory::getPageCrawler;

        // Start the crawl. This is a blocking operation, meaning that your code
        // will reach the line after this only when crawling is finished.
        controller.start(factory, numberOfCrawlers);
    }
}
