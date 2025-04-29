package com.example.back.restControllers;

import com.example.back.entities.Url;
import com.example.back.services.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/redirect")
public class RedirectController {
    @Autowired
    UrlService urlService;

    @GetMapping("/{id}")
    public ResponseEntity<Void> redirectToOriginalUrl(@PathVariable("id") Long id) {
        Url url = urlService.getById(id);
        if (url != null && url.getUrlUnshorted() != null) {
            return ResponseEntity
                    .status(302)
                    .header("Location", url.getUrlUnshorted())
                    .build();
        }
        return ResponseEntity.status(404).build();
    }

}
