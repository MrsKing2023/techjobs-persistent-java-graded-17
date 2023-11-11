package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

@Entity
public class Skill extends AbstractEntity {

    @ManyToMany(mappedBy="skills")
    private Job job;

    private String description;

    public Skill(String description) {
        this.description = description;
    }

    public Skill() {}


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}
