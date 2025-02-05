package com.portafolio.jeanAngel.controller;

import com.portafolio.jeanAngel.dto.DataListProject;
import com.portafolio.jeanAngel.dto.DataProjectDTO;
import com.portafolio.jeanAngel.dto.DataUpdateProjectDTO;
import com.portafolio.jeanAngel.dto.RegisterProjectDTO;
import com.portafolio.jeanAngel.services.IProjectService;
import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import java.net.URI;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/projects")
// @SecurityRequirement(name = "bearer-key")
public class ProjectController {

    private final IProjectService iProjectService;
    private final PagedResourcesAssembler<DataListProject> pagedResourcesAssembler;

    @Autowired
    public ProjectController(IProjectService iProjectService, PagedResourcesAssembler<DataListProject> pagedResourcesAssembler) {
        this.iProjectService = iProjectService;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @PostMapping
    public ResponseEntity<DataProjectDTO> registerProyect(@RequestBody @Valid RegisterProjectDTO registerProjectDTO,
                                                          UriComponentsBuilder uriComponentsBuilder) {
        DataProjectDTO savedProject = iProjectService.createProject(registerProjectDTO);
        URI url = uriComponentsBuilder.path("/api/projects/{id}")
                .buildAndExpand(savedProject.id())
                .toUri();
        return ResponseEntity.created(url).body(savedProject);
    }

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<DataListProject>>> listProyects(
            @PageableDefault(size = 12, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {

        Page<DataListProject> projectsPage = iProjectService.listAllProjects(pageable);

        PagedModel<EntityModel<DataListProject>> pagedModel = pagedResourcesAssembler.toModel(
                projectsPage,
                project -> EntityModel.of(
                        project,
                        WebMvcLinkBuilder.linkTo(
                                WebMvcLinkBuilder.methodOn(ProjectController.class).returnProyect(project.id())
                        ).withSelfRel()
                )
        );

        return ResponseEntity.ok(pagedModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataProjectDTO> returnProyect(@PathVariable Long id) {
        DataProjectDTO dataProject = iProjectService.getProjectById(id);
        return ResponseEntity.ok(dataProject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataProjectDTO> updateProject(
            @PathVariable Long id, @RequestBody @Valid DataUpdateProjectDTO dataUpdateProject) {
        DataProjectDTO updatedProject = iProjectService.updateProject(
                new DataProjectDTO(id, dataUpdateProject.name(), dataUpdateProject.description(), dataUpdateProject.technologies(), dataUpdateProject.githubUrl()
                ));
        return ResponseEntity.ok(updatedProject);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable Long id) {
        try {
            iProjectService.deleteProject(id); // Intentamos eliminar el proyecto
            Map<String, String> response = new HashMap<>();
            response.put("message", "Project successfully deleted");
            return ResponseEntity.ok(response); // Devuelve un mensaje personalizado con estado 200 OK
        } catch (RuntimeException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Project not found");
            return ResponseEntity.status(404).body(errorResponse); // Devuelve un mensaje de error con estado 404
        }
    }

}
