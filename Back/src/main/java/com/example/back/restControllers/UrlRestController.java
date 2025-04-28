package com.example.back.restControllers;

import com.example.back.entities.Response;
import com.example.back.entities.Url;
import com.example.back.services.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/url")
public class UrlRestController {
    @Autowired
    UrlService urlService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Response> getById(@PathVariable(value = "id") Long id){
        if (id!=null){
            Url url = urlService.getById(id);
            if (url!=null){
                return ResponseEntity.ok(new Response("Url Inteira Recuperada com sucesso!",false,url));
            }
            return ResponseEntity.status(404).body(new Response("Nenhuma url encontrada com esse id",false,null));
        }
        return ResponseEntity.status(404).body(new Response("ID NULO!",false,null));
    }
}
