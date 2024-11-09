package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Objects;

@MappedSuperclass // MyToDo
public abstract class AbstractEntity {

    @Id // MyToDo
    @GeneratedValue(strategy = GenerationType.IDENTITY) // MyToDo Annotation for Hibernate to flag as primary key
    private int id;

    @NotNull(message = "⚠\uFE0F Field cannot be empty") // MyToDo
    @Size(min=1, max=255, message = "⚠\uFE0F Text length is out of bounds") // MyToDo
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity that = (AbstractEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
