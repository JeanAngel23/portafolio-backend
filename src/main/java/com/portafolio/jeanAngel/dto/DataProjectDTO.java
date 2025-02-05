package com.portafolio.jeanAngel.dto;


import jakarta.validation.constraints.NotNull;

public record DataProjectDTO(
        @NotNull
        Long id,
        String name,
        String description,
        String technologies,
        String githubUrl
)
{

}
