package ua.axiom.model.actors;

import javax.persistence.*;

@Entity
public class Car {
    public enum Class{
        BUDGET(0.75F),
        BUSINESS(1.F),
        PREMIUM(1.5F);

        public float multiplier;

        Class(float multiplier) {
            this.multiplier = multiplier;
        }
    };

    public Car() {
    }

    @Id
    private Long id;

    private String modelName;
    @Enumerated(EnumType.STRING)
    private Class aClass;

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}