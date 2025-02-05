package com.portafolio.jeanAngel.services;

import com.portafolio.jeanAngel.dto.DataListProject;
import com.portafolio.jeanAngel.dto.DataProjectDTO;
import org.springframework.data.domain.Pageable;
import com.portafolio.jeanAngel.dto.RegisterProjectDTO;
import com.portafolio.jeanAngel.entity.Project;
import com.portafolio.jeanAngel.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class ProjectService implements IProjectService{

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }



    @Override
    public Page<DataListProject> listAllProjects(Pageable pageable) {
        return projectRepository.findAll(pageable)
                .map(project -> new DataListProject(
                        project.getId(),
                        project.getName(),
                        project.getDescription(),
                        project.getTechnologies(),
                        project.getGithubUrl()
                ));
    }

    @Override
    public DataProjectDTO getProjectById(Long id) {
        Project project =projectRepository.getReferenceById(id);
        return new DataProjectDTO(project.getId(), project.getName(), project.getDescription(), project.getTechnologies(), project.getGithubUrl());
    }

    @Override
    public DataProjectDTO createProject(RegisterProjectDTO registerProjectDTO) {
        Project project = projectRepository.save(new Project(registerProjectDTO));
        return new DataProjectDTO(project.getId(), project.getName(), project.getDescription(),project.getTechnologies(), project.getGithubUrl());
    }

    @Override
    @Transactional
    public DataProjectDTO updateProject(DataProjectDTO dataUpdateProject) {
        // Verificar si el proyecto existe
        Project project = projectRepository.findById(dataUpdateProject.id())
                .orElseThrow(() -> new RuntimeException("Project with ID " + dataUpdateProject.id() + " not found"));
        // Actualizar datos del proyecto
        project.updateData(dataUpdateProject);
        // Guardar los cambios explícitamente
        projectRepository.save(project);
        // Retornar el DTO actualizado
        return new DataProjectDTO(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getTechnologies(),
                project.getGithubUrl()
        );
    }





    @Override
    public void deleteProject(Long id) {
        Optional<Project> optionalProject = projectRepository.findById(id);

        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            projectRepository.delete(project); // Cambiar esto si necesitas un borrado lógico.
        } else {
            throw new RuntimeException("Project with id " + id + " not found.");
        }
    }

}
