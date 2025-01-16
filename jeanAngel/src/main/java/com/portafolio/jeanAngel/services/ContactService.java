package com.portafolio.jeanAngel.services;

import com.portafolio.jeanAngel.dto.ContactDTO;
import com.portafolio.jeanAngel.entity.Contact;
import com.portafolio.jeanAngel.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    public Optional<ContactDTO> getContactInfo() {
        Optional<Contact> contact = contactRepository.findById(1L); // Asumiendo un único registro de contacto
        return contact.map(c -> new ContactDTO(
                c.getEmail(),
                c.getPhoneNumber(),
                c.getLinkedinUrl(),
                c.getGithubUrl(),
                c.getAddress()
        ));
    }
}
