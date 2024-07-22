package org.example.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
public class Planet {

    @Id
    @Column(length = 10)
    private String id;

    @Column(nullable = false, length = 500)
    private String name;

    // Constructors, getters, and setters
    public Planet() {}

    public Planet(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
