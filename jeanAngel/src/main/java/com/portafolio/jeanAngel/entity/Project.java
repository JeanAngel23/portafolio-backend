package com.portafolio.jeanAngel.entity;

import com.portafolio.jeanAngel.dto.DataProjectDTO;
import com.portafolio.jeanAngel.dto.RegisterProjectDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String description;
    private String technologies;
    private String githubUrl;

    //Tener en cuenta el campo Status para realizar el delete logico en el crud

    //Contructor basio ya que presentamos problemas con Lombok
    public Project() {
    }

    public Project (RegisterProjectDTO registerProjectDTO){
        this.name = registerProjectDTO.name();
        this.description = registerProjectDTO.description();
        this.technologies = registerProjectDTO.technologies();
        this.githubUrl = registerProjectDTO.githubUrl();

    }

    public void updateData(DataProjectDTO dataProjectDTO){
        if (dataProjectDTO.name() != null) {
            this.name = dataProjectDTO.name();
        }
        if (dataProjectDTO.description() != null) {
            this.description = dataProjectDTO.description();
        }
        if (dataProjectDTO.technologies() != null) {
            this.technologies = dataProjectDTO.technologies();
        }
        if (dataProjectDTO.githubUrl() != null) {
            this.githubUrl = dataProjectDTO.githubUrl();
        }

    }




    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }

    public String getTechnologies() {
        return technologies;
    }

    public void setTechnologies(String technologies) {
        this.technologies = technologies;
    }

}
