package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.entities.Employee;
import org.example.entities.Product;
import org.example.persistence.CustomPersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        // The following code (commented out) shows an alternate method where you would use the default
        // "persistence.xml" configuration file instead of programmatically creating the EntityManagerFactory.
        // This is an XML-based configuration approach.
        // EntityManagerFactory entityManagerFactoryForXML = Persistence.createEntityManagerFactory("my-persistence-unit");

        // The line below shows how to programmatically create the EntityManagerFactory
        // using Hibernate as the persistence provider, along with a custom configuration for the persistence unit.
        // CustomPersistenceUnitInfo holds specific configurations like database connection settings, entities, etc.
        // It is a more dynamic and flexible approach to configuration compared to XML-based configurations.
        EntityManagerFactory entityManagerFactory = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(), new HashMap<>());

        // Creating the EntityManager instance here. The EntityManager is the main interface for interacting with
        // the persistence context, which means managing entities, executing queries, and performing CRUD operations.
        // It works within the transaction context.
        EntityManager entityManager = entityManagerFactory.createEntityManager(); // CONTEXT

        try {
            // Begin the transaction. All database-related operations must occur within a transaction.
            // The transaction is needed to ensure that all operations are performed atomically.
            entityManager.getTransaction().begin();

            // This is where we would add a new Employee to the database. The employee instance is created, populated
            // with data, and persisted to the database.
            // The code below is commented out but would work as follows:
            // - Create a new Employee object
            // - Set the employee's properties (address, name, id)
            // - Persist it to the database using entityManager.persist() to save the entity.
            //
            //             Employee employee = new Employee();
            //             employee.setAddress("Example address 2");
            //             employee.setName("Example name 2");
            //             employee.setId(2);
            //             entityManager.persist(employee);


            // Instead of adding a new employee, here we're retrieving an existing employee from the database.
            // The 'find' method is used to look up the Employee by its primary key (id).
            // In this case, we're finding the employee with an ID of 1, and then printing their name to the console.
            // The 'find' method retrieves the object from the database if it exists or returns null if not found.
            Employee employeeProp = entityManager.find(Employee.class, 1);
            System.out.println(employeeProp.getName()); // Output the name of the employee to the console.



            // Renaming the employee name
            employeeProp.setName("Doguhan");
            System.out.println(employeeProp.getName()); // Output the name of the employee to the console.


            // Delete an employee
            // Employee employeeProp2 = entityManager.find(Employee.class, 2);
            // entityManager.remove(employeeProp2);


            // Commit the transaction. This is where all the changes (if any) are flushed to the database.
            // If there were no changes, the commit just marks the transaction as successful.
            // Without this, no changes will be saved to the database.
            entityManager.getTransaction().commit();


            // Nothing has been changed because our value has been stored in the context but hasn't been
            // committed to the database.
            employeeProp.setName("Renaming the employee without commit the transaction");
            System.out.println(employeeProp.getName()); // Output the name of the employee to the console.

        } finally {
            // Ensure that the EntityManager is closed, releasing resources such as database connections.
            // This is important to avoid memory leaks and to properly manage the connection pool.
            entityManager.close();
        }

    }
}
