package com.example.back.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "url")
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="url_id")
    private Long id;

    @Column(name = "url_unshorted")
    private String urlUnshorted;

    public Url(Long id, String urlUnshorted) {
        this.id = id;
        this.urlUnshorted = urlUnshorted;
    }

    public Url() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrlUnshorted() {
        return urlUnshorted;
    }

    public void setUrlUnshorted(String urlUnshorted) {
        this.urlUnshorted = urlUnshorted;
    }
}
