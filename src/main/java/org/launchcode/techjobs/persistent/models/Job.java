package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Job extends AbstractEntity{

    // MyToDo Remove the redundant fields from Job
    /*@Id
    @GeneratedValue
    private int id;
    private String name;
    private String employer;
    */

    // MyToDo Update your Job model class to fit its many-to-many relationship with skills.
    // Don't initialize or make final!
    @ManyToMany
    private List<Skill> skills;

    // MyToDo Replace the type of the field employer to be of type Employer.
    @ManyToOne
    private Employer employer;

    //private String skills;

    public Job() {
    }

    // Initialize the id and value fields.
    public Job(Employer anEmployer, List<Skill> someSkills) { // MyToDo Change to from String to List<Skill>
        super();
        this.employer = anEmployer;
        this.skills = someSkills;
    }

    // Getters and setters
   /*  public String getName() {  return name;  }
       public void setName(String name) { this.name = name;
   }*/

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}
