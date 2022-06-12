package com.dreamteam3.company.uploader.service;

import com.dreamteam3.company.uploader.enums.ResourceType;
import com.dreamteam3.company.uploader.factory.PageCrawlerFactory;
import com.dreamteam3.data.model.Company;
import com.dreamteam3.data.service.CompanyService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
@Scope("prototype")
@Data
@RequiredArgsConstructor
public class CompanyUploader {

    private ResourceType resourceType;
    private String resource;

    private final CompanyService companyService;
    private final PageCrawlerFactory pageCrawlerFactory;

    public void upload() {
        try {
            switch (resourceType) {
                case FILE:
                    uploadFromFile();
                    break;
                case DATABASE:
                    uploadFromDatabase();
                    break;
            }
        } catch (Exception e) {
            log.error("", e);
        }
    }

    private void uploadFromFile() {
        List<Company> companies = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(new File(resource));
             XSSFWorkbook workbook = new XSSFWorkbook(file);) {

            XSSFSheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {

                Company.CompanyBuilder builder = Company.builder();
                //TODO подумать, что делать, если в файле нет данных
                builder.okved("");
                builder.address("");

                int rowNumber = row.getRowNum();
                if (rowNumber == 0) {
                    continue;
                }

                for (Cell cell : row) {
                    CellType cellType = cell.getCellType();
                    String value;
                    if (cellType == CellType.NUMERIC) {
                        value = cell.getNumericCellValue() + "";
                    } else {
                        value = cell.getStringCellValue();
                    }

                    int columnIndex = cell.getColumnIndex();
                    switch (columnIndex) {
                        case 1:
                            builder.inn(value);
                            break;
                        case 2:
                            builder.name(value);
                            break;
                        case 4:
                            builder.url(value);
                            break;
                    }
                }
                companies.add(builder.build());
            }

        } catch (IOException e) {
            log.error("Uploading from file failed", e);
        }

        List<String> inns = companies.stream()
                .map(Company::getInn)
                .collect(Collectors.toList());
        List<String> savedInns = companyService.findAllByInnIn(inns).stream()
                .map(Company::getInn)
                .collect(Collectors.toList());

        List<Company> newCompanies = companies.stream()
                .filter(company -> !savedInns.contains(company.getInn()))
                .collect(Collectors.toList());
        companyService.saveAll(newCompanies);
    }

    private void uploadFromDatabase() throws IOException {
        Document doc = Jsoup.connect(resource).get();
        Elements companyTags = doc.select("label");

        for (Element element : companyTags) {
            List<Node> nodes = element.childNodes();
            if (nodes.stream()
                    .anyMatch(node -> node.nodeName().equals("span") && node.hasAttr("status_0"))) {
                continue;
            }

            Optional<Node> info = nodes.stream()
                    .filter(node -> node.nodeName().equals("span") && !node.hasAttr("status_0"))
                    .findAny();
            if (info.isEmpty()) {
                continue;
            }
            Company.CompanyBuilder builder = Company.builder();
            builder.okved("");
            List<Node> details = info.get().childNodes();
            for (Node node : details) {
                if (node.toString().startsWith("ИНН")) {
                    builder.inn(node.toString());
                } else if (node.toString().startsWith("адрес")) {
                    builder.address(node.toString());
                } else if (!node.toString().equals("<br>")) {
                    builder.name(node.toString());
                }
            }

            Optional<Node> link = nodes.stream()
                    .filter(node -> node.nodeName().equals("a"))
                    .findAny();
            if (link.isPresent()) {
                builder.url(link.get().attr("href"));
            }

            Company company = builder.build();
            companyService.save(company);
        }
    }

}
