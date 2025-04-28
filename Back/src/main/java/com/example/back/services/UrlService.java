package com.example.back.services;

import com.example.back.entities.Url;
import com.example.back.repositories.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlService {
    @Autowired
    UrlRepository urlRepository;

    public Url getById(Long id) {
        return urlRepository.findById(id).orElse(null);
    }
}
