package com.portafolio.jeanAngel.controller;

import com.portafolio.jeanAngel.dto.ContactDTO;
import com.portafolio.jeanAngel.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping
    public ResponseEntity<ContactDTO> getContactInfo() {
        return contactService.getContactInfo()
                .map(ResponseEntity::ok) // Devuelve 200 OK con el cuerpo
                .orElse(ResponseEntity.notFound().build()); // Devuelve 404 si no se encuentra
    }
}
