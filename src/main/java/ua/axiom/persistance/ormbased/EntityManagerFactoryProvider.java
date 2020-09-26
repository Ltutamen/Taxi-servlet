package ua.axiom.persistance.ormbased;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryProvider {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("TAXI_SER");
}
