package com.portafolio.jeanAngel.services;

import com.portafolio.jeanAngel.dto.SkillDTO;
import com.portafolio.jeanAngel.entity.Skill;
import com.portafolio.jeanAngel.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    // Método para obtener todas las habilidades
    public List<SkillDTO> getAllSkills() {
        // Recuperar todas las entidades Skill de la base de datos
        List<Skill> skills = skillRepository.findAll();

        return skills.stream()
                .map(s -> new SkillDTO(
                        s.getName(),
                        s.getProficiencyLevel()
                ))
                .collect(Collectors.toList());
    }
}
