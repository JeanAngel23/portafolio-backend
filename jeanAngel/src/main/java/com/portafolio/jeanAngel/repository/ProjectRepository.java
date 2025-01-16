package com.portafolio.jeanAngel.repository;

import com.portafolio.jeanAngel.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

}
//Page<Project> findByStatusTrue (Pageable pageable); implementar cuando se implemente el status