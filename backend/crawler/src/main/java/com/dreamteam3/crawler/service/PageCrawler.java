package com.dreamteam3.crawler.service;

import com.dreamteam3.data.model.WebPage;
import com.dreamteam3.data.service.WebPageService;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class PageCrawler extends WebCrawler {

    private final WebPageService webPageService;

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        //TODO добавить логику по определению, стоит ли ходить по новому адресу
        return true;
    }

    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();
        log.info("URL: " + url);

        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String text = htmlParseData.getText();
            String html = htmlParseData.getHtml();
            Set<WebURL> links = htmlParseData.getOutgoingUrls();

            WebPage webPage = WebPage.builder()
                    .url(url)
                    .content(html)
                    .companyId(1L)
                    .build();
            webPageService.save(webPage);

            log.info("Text length: " + text.length());
            log.info("Html length: " + html.length());
            log.info("Number of outgoing links: " + links.size());
        }
    }

}
