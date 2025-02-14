// ----------------------------- STEP 1: PACKAGE DEFINITION -----------------------------

// We start by defining the package where this entity belongs.
// The package structure follows the convention of "org.example.entities",
// meaning this file is located inside the "entities" directory.

package org.example.entities;

// ----------------------------- STEP 2: IMPORT NECESSARY ANNOTATIONS -----------------------------

// Import necessary JPA annotations to define this class as an entity in a database.
// - @Entity: Marks this class as a JPA entity, meaning it will be mapped to a database table.
// - @Id: Defines primary key fields.
// - @IdClass: Specifies a composite key class (StockKeys) for this entity.

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import org.example.entities.keys.StockKeys;

// ----------------------------- STEP 3: DEFINE THE ENTITY CLASS -----------------------------

// We define the "Stock" class as an entity, meaning it will be mapped to a database table.
// The table name will be automatically inferred as "Stock" unless otherwise specified.

@Entity  // This annotation tells Hibernate that this is a database entity.
@IdClass(StockKeys.class)  // This annotation specifies that the primary key is composed of multiple fields.

public class Stock {  // The entity class begins here.

// ----------------------------- STEP 4: DEFINE COMPOSITE PRIMARY KEYS -----------------------------

// Since we are using @IdClass, we need to define multiple primary keys.
// The class "StockKeys" (which must be defined separately) contains the composite key structure.

    @Id  // This field is part of the composite primary key.
    private String code;

    @Id  // This field is also part of the composite primary key.
    private long number;

// ----------------------------- STEP 5: DEFINE NON-PRIMARY FIELDS -----------------------------

// Other fields that are NOT part of the primary key are defined normally.
// Here, "color" is just a regular column in the table.

    private String color;

// ----------------------------- STEP 6: CONSTRUCTORS, GETTERS, AND SETTERS -----------------------------

// Getters and setters are used to access and modify the object's properties.
// These methods allow Hibernate to read and update the entity's fields.

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}  // End of Stock class

// ----------------------------- STEP 7: UNDERSTANDING @IdClass -----------------------------

// The annotation @IdClass(StockKeys.class) tells Hibernate that the "Stock" entity uses a COMPOSITE KEY.
// This means the primary key consists of more than one field (code + number).

// The class "StockKeys" should be defined in the package "org.example.entities.keys"
// It must:
//  - Have the same field names as the primary key fields in Stock (code, number).
//  - Implement "Serializable" to be a valid composite key.

// ----------------------------- STEP 8: IMPORTANT NOTES -----------------------------

// 1. The @Entity annotation tells Hibernate that this class represents a table in the database.
// 2. @IdClass is used to define a COMPOSITE PRIMARY KEY, meaning multiple fields form a unique identifier.
// 3. Each field marked with @Id is part of the composite key.
// 4. Other fields (like "color") are just normal columns in the table.
// 5. The getters and setters allow Hibernate to access and modify the entity fields dynamically.

// ----------------------------- STEP 9: DATABASE TABLE REPRESENTATION -----------------------------

// When Hibernate translates this entity into an SQL table, it will generate:

// CREATE TABLE stock (
//    code VARCHAR(255) NOT NULL,
//    number BIGINT NOT NULL,
//    color VARCHAR(255),
//    PRIMARY KEY (code, number)
// );

// Here:
// - "code" is part of the primary key.
// - "number" is part of the primary key.
// - "color" is a normal column.
