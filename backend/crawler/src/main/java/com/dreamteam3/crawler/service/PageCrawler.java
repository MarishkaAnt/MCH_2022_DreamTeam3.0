package com.dreamteam3.crawler.service;

import com.dreamteam3.data.model.Company;
import com.dreamteam3.data.model.WebPage;
import com.dreamteam3.data.service.CompanyService;
import com.dreamteam3.data.service.WebPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class PageCrawler implements Callable<Boolean> {

    private static final Pattern EXCLUSIONS
            = Pattern.compile(".*(\\.(css|js|xml|gif|jpg|png|mp3|mp4|zip|gz|pdf))$");

    private final CompanyService companyService;
    private final WebPageService webPageService;

    private final List<WebPage> webPages = new ArrayList<>();
    private int counter = 0;

    @Override
    public Boolean call() throws Exception {
        try {
            List<Company> companies = companyService.findAll().stream()
                    .filter(company -> company.getUrl() != null && !company.getUrl().equals("Сведения отсутствуют"))
                    .collect(Collectors.toList());

            WebPage.WebPageBuilder builder = WebPage.builder();
            for (Company company : companies) {
                String url = company.getUrl();
                if (!url.startsWith("http")) {
                    url = "https://" + url;
                }
                crawl(url, company, company.getUrl(), builder);
                webPageService.saveAll(webPages);
                webPages.clear();
            }
        } catch (IOException e) {
            log.error("", e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return true;
    }

    private void crawl(String url, Company company, String baseUrl, WebPage.WebPageBuilder builder) throws IOException, InterruptedException {
        if (Thread.currentThread().isInterrupted()) {
            throw new InterruptedException();
        }
        if (!url.startsWith(baseUrl)
                || EXCLUSIONS.matcher(url).matches()
                || webPages.stream()
                .anyMatch(webPage -> webPage.getUrl().equals(url))) {
            return;
        }

        Document doc;
        try {
            Thread.sleep(200);
            doc = Jsoup.parse(new URL(url), 1000);
        } catch (IOException e) {
            log.error("", e);
            return;
        }

        WebPage webPage = builder
                .company(company)
                .url(url)
                .html(doc.html())
                .text(doc.text())
                .title(doc.title())
                .build();
        webPages.add(webPage);

        if (counter > 2) {
            return;
        }

        Elements links = doc.select("a[href]");
        for (Element link : links) {
            counter++;
            crawl(link.attr("abs:href"), company, baseUrl, builder);
            counter--;
        }
    }

}
