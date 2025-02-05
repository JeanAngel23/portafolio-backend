package com.portafolio.jeanAngel.services;

import com.portafolio.jeanAngel.dto.DataListProject;
import com.portafolio.jeanAngel.dto.DataProjectDTO;
import com.portafolio.jeanAngel.dto.RegisterProjectDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;




public interface IProjectService {


    Page<DataListProject> listAllProjects(Pageable pageable);

    DataProjectDTO getProjectById(Long id);

    DataProjectDTO createProject(RegisterProjectDTO registerProjectDTO);

    DataProjectDTO updateProject(DataProjectDTO dataUpdateProject);

    void deleteProject(Long id);

}
