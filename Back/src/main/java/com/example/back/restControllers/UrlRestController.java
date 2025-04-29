package com.example.back.restControllers;

import com.example.back.entities.ResponseController;
import com.example.back.entities.Url;
import com.example.back.services.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/url")
@CrossOrigin
public class UrlRestController {
    @Autowired
    UrlService urlService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseController> getById(@PathVariable(value = "id") Long id){
        if (id!=null){
            Url url = urlService.getById(id);
            if (url!=null){
                return ResponseEntity.ok(new ResponseController("Url Inteira Recuperada com sucesso!",false,url));
            }
            return ResponseEntity.status(404).body(new ResponseController("Nenhuma url encontrada com esse id",true,null));
        }
        return ResponseEntity.status(404).body(new ResponseController("ID NULO!",true,null));
    }

    @GetMapping()
    public ResponseEntity<ResponseController> getAll (){
        List<Url> urls = urlService.getAll();
        if (urls!=null && !urls.isEmpty()){
            return ResponseEntity.ok(new ResponseController("Urls inteiras recuperadas com sucesso",false,urls));
        }
        return ResponseEntity.status(404).body(new ResponseController("Erro ao recuperar as urls",true,null));
    }

    @PostMapping
    public ResponseEntity<ResponseController> addOne(@RequestBody Url url){
        Url urlCadastrada = urlService.addOne(url);
        if (urlCadastrada!=null){
            return ResponseEntity.ok(new ResponseController("Url criada com sucesso",false,urlCadastrada));
        }
        return ResponseEntity.status(404).body(new ResponseController("Erro ao cadastrar url",true,null));
    }

    @PutMapping
    public ResponseEntity<ResponseController> updateOne (@RequestBody Url url){
        Url urlAlterada = urlService.change(url);
        if (urlAlterada!=null){
            return ResponseEntity.ok(new ResponseController("Url alterada com sucesso",false,urlAlterada));
        }
        return ResponseEntity.status(400).body(new ResponseController("Erro ao alterar Url",true,null));
    }
}
