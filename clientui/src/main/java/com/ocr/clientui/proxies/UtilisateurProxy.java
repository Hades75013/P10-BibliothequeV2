package com.ocr.clientui.proxies;


import com.ocr.clientui.beans.UtilisateurBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "microservice-user", url = "localhost:8082")
public interface UtilisateurProxy {

    @PostMapping(value="/saveUser")
    UtilisateurBean sauverUtilisateur(@RequestBody UtilisateurBean utilisateurBean);

    @GetMapping("/login/{email}")
    UtilisateurBean connectionUtilisateur(@PathVariable String email);

    @GetMapping("/user/{id}")
    UtilisateurBean recupererUtilisateur(@PathVariable int id);



}
