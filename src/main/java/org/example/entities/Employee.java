package org.example.entities;


import jakarta.persistence.*;

// @Entity(name="") <--  JPA specifies the entity and how it should be named.
@Entity
// Specify the name of this table in your database.
// @Table(name = "employee")
public class Employee {

    @Id
    /*
    GenerationType.IDENTITY: This strategy will help us to generate the primary key value by the database itself using the auto-increment column option. It relies on the database’s native support for generating unique values.
    GenerationType.AUTO: This is a default strategy and the persistence provider which automatically selects an appropriate generation strategy based on the database usage.
    GenerationType.TABLE: This strategy uses a separate database table to generate primary key values. The persistence provider manages this table and uses it to allocate unique values for primary keys.
    GenerationType.SEQUENCE: This generation-type strategy uses a database sequence to generate primary key values. It requires the usage of database sequence objects, which varies depending on the database which is being used.
    */
    @GeneratedValue(strategy = GenerationType.AUTO)
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
