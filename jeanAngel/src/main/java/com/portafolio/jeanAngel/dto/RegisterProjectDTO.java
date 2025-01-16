package com.portafolio.jeanAngel.dto;


import jakarta.validation.constraints.NotBlank;

public record RegisterProjectDTO(
        @NotBlank(message = "El nombre del proyecto es obligatorio")
        String name,
        @NotBlank(message = "Es necesario agregar una descripcion del proyecto")
        String description,
        @NotBlank(message = "Debes indicar que tecnologias utilizaste para este proyecto")
        String technologies,
        String githubUrl

) {
}
