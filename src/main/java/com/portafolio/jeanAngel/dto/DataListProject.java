package com.portafolio.jeanAngel.dto;

import com.portafolio.jeanAngel.entity.Project;

public record DataListProject(
        Long id,
        String name,
        String description,
        String technologies,
        String githubUrl
) {
    public DataListProject(Project project){
        this(project.getId(), project.getName(), project.getDescription(), project.getTechnologies(), project.getGithubUrl());
    }
}
