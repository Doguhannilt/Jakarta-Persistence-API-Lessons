package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.entities.Product;
import org.example.persistence.CustomPersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        // FOR XML
        // EntityManagerFactory entityManagerFactoryForXML = Persistence.createEntityManagerFactory("my-persistence-unit");

        // Programmatic Approach
        EntityManagerFactory entityManagerFactory = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(), new HashMap<>());


        EntityManager entityManager = entityManagerFactory.createEntityManager(); // CONTEXT

        try {
            entityManager.getTransaction().begin();

            // Add to CONTEXT {Not an }
            Product t = new Product();
            t.setId(2L);
            t.setName("O");
            entityManager.persist(t);


            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }


    }
}