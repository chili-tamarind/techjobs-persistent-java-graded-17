package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employer extends AbstractEntity {

    @NotNull(message = "⚠\uFE0F Location cannot be empty") // MyToDo
    @Size(min=1, max=255, message = "⚠\uFE0F Text length is out of bounds") // MyToDo
    private String location;

    // MyToDo - No-argument constructor required for Hibernate to create an object
    public Employer() {}

    // MyToDo
    @OneToMany
    @JoinColumn(name = "employer_id")
    private List<Job> jobs = new ArrayList<>();

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public List<Job> getJobs() { return jobs; }
    public void setJobs(List<Job> jobs) { this.jobs = jobs; }

}
