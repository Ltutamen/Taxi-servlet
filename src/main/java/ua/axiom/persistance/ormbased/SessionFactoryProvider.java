package ua.axiom.persistance.ormbased;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ua.axiom.core.annotations.Component;
import ua.axiom.core.annotations.InitMethod;

@Component
public class SessionFactoryProvider {
    private StandardServiceRegistry serviceRegistry;
    private SessionFactory sessionFactory;

    @InitMethod
    private void init() {
        serviceRegistry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        MetadataSources metadataSources = new MetadataSources(serviceRegistry);

        Metadata metadata = metadataSources
                .getMetadataBuilder()
                .build();

        sessionFactory = metadata
                .getSessionFactoryBuilder()
                .build();

    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void shutdown() {
        if(serviceRegistry != null) {
            StandardServiceRegistryBuilder.destroy(serviceRegistry);
        }
    }

}