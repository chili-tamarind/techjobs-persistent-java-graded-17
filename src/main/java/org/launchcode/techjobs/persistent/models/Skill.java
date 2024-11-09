package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Skill extends AbstractEntity {

    @NotEmpty(message = "⚠\uFE0F Skills cannot be empty") // MyToDo
    @Size(min=1, max=1000, message = "Text length is out of bounds") // MyToDo
    private String description;

    // MyToDo Add a jobs field
    @ManyToMany(mappedBy="skills")
    private List<Job> jobs = new ArrayList<>();

    public Skill() {
    }

    public String getDescription() { return description; }
    public void setDescription(String description) {this.description = description; }

    // MyToDo
    //public Job getJobs() { return jobs; }
    //public void setJobs(Job jobs) { this.jobs = jobs; }

    public List<Job> getJobs() { return jobs; }
    public void setJobs(List<Job> jobs) { this.jobs = jobs; }

}
