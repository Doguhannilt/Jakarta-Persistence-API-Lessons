package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.entities.Employee;
import org.example.entities.Stock;
import org.example.entities.Student;
import org.example.persistence.CustomPersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Map<String, String> props = new HashMap<>();

        // It's a special feature Hibernate has. You can see details about the SQL
        // process and its mutation that JPA does when you run the project.
        // Example: Hibernate: select e1_0.id,e1_0.address,e1_0.name from Employee e1_0 where e1_0.id=?
        props.put("hibernate.show_sql","true");

        /*
        * hibernate.hbm2ddl.auto Automatically validates or exports schema DDL
        * to the database when the SessionFactory is created. With create-drop,
        * the database schema will be dropped when the SessionFactory is closed explicitly.
            validate: validate the schema, makes no changes to the database.
            create-only: database creation will be generated.
            drop: database dropping will be generated.
            update: update the schema.
            create: creates the schema, destroying previous data.
            create-drop: drop the schema when the SessionFactory is closed explicitly, typically when the application is stopped.
            none: does nothing with the schema, makes no changes to the database
        */
        props.put("hibernate.hbm2ddl.auto", "create");

        // The following code (commented out) shows an alternate method where you would use the default
        // "persistence.xml" configuration file instead of programmatically creating the EntityManagerFactory.
        // This is an XML-based configuration approach.
        // EntityManagerFactory entityManagerFactoryForXML = Persistence.createEntityManagerFactory("my-persistence-unit");

        // The line below shows how to programmatically create the EntityManagerFactory
        // using Hibernate as the persistence provider, along with a custom configuration for the persistence unit.
        // CustomPersistenceUnitInfo holds specific configurations like database connection settings, entities, etc.
        // It is a more dynamic and flexible approach to configuration compared to XML-based configurations.
        EntityManagerFactory entityManagerFactory = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(),props);

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

                         // Employee employee = new Employee();
                         // employee.setAddress("Example address 2");
                         // employee.setName("Example name 2");
                         // entityManager.persist(employee);

            // Instead of adding a new employee, here we're retrieving an existing employee from the database.
            // The 'find' method is used to look up the Employee by its primary key (id).
            // In this case, we're finding the employee with an ID of 1, and then printing their name to the console.
            // The 'find' method retrieves the object from the database if it exists or returns null if not found.
            // Employee employeeProp = entityManager.find(Employee.class, 1);
            // System.out.println(employeeProp.getName()); // Output the name of the employee to the console.


            // Renaming the employee name
            // employeeProp.setName("Doguhan");
            // System.out.println(employeeProp.getName());  Output the name of the employee to the console.

            // Delete an employee
            // entityManager.remove(employeeProp2);

            /*
            * The find method always returns an entity object.
            * Hibernate initializes its attributes based on the defined FetchTypes.
            * If you're using a default mapping, Hibernate fetches all basic attributes and initializes all to-one associations.
            * The getReference method returns a reference to an entity object.
            */
            // Employee employeeProp2 = entityManager.find(Employee.class, 2);
            // Employee reference = entityManager.getReference(Employee.class, 2);
            // System.out.println(employeeProp2);
            // System.out.println(reference);

            // Reload all the data.
            // entityManager.refresh(employeeProp2);


            Stock stock = new Stock();
            stock.setCode("abc");
            stock.setNumber(10);
            stock.setColor("Rosso");
            entityManager.persist(stock);

            // Commit the transaction. This is where all the changes (if any) are flushed to the database.
            // If there were no changes, the commit just marks the transaction as successful.
            // Without this, no changes will be saved to the database.
            entityManager.getTransaction().commit();


            // Nothing has been changed because our value has been stored in the context but hasn't been
            // committed to the database.
            // employeeProp.setName("Set a new name to the employee without commit the transaction");
        } finally {
            // Ensure that the EntityManager is closed, releasing resources such as database connections.
            // This is important to avoid memory leaks and to properly manage the connection pool.
            entityManager.close();
        }
    }
}
