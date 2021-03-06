package com.dreamteam3.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "companies")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String inn;
    private String okved;
    private String address;
    private String url;

    @OneToMany
    @JoinColumn(name = "company_id")
    private List<Product> products;

    @OneToMany
    @JoinColumn(name = "company_id")
    private List<WebPage> webPages;

}
