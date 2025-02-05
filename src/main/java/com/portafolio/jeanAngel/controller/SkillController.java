package com.portafolio.jeanAngel.controller;

import com.portafolio.jeanAngel.dto.SkillDTO;
import com.portafolio.jeanAngel.services.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/skills")
@RestController
public class SkillController {

    @Autowired
    private SkillService skillService;

    @GetMapping
    public List<SkillDTO> getAllSkills(){
        return skillService.getAllSkills();
    }
}
