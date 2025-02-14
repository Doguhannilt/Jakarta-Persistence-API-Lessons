package org.example.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.example.entities.generators.UUIDGenerator;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

@Entity
public class Student {

    @Id
    @GenericGenerator(name = "UUIDGENERATOR", type = UUIDGenerator.class)
    @GeneratedValue(generator = "UUIDGENERATOR")
    private String id;

    private String name;
    private String address;


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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
