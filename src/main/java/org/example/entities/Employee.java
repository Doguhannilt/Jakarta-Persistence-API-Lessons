package org.example.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// @Entity(name="") <--  JPA specifies the entity and how it should be named.
@Entity
// Specify the name of this table in your database.
// @Table(name = "employee")
public class Employee {

    @Id
    @Column(name="id") // Specify the column name, or your variable name is going to be the column name.
    private int id;

    private String name;
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
