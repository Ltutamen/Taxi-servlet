package ua.axiom.model.actors;

import ua.axiom.persistance.jdbcbased.Persistent;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Car extends Persistent<Long> {
    public enum Class{
        BUDGET(0.75F),
        BUSINESS(1.F),
        PREMIUM(1.5F);

        public float multiplier;

        Class(float multiplier) {
            this.multiplier = multiplier;
        }
    };

    public Car(long id) {
        super(id);
    }

    @NotNull
    @Size(min = 4, max = 40)
    private String modelName;

    @NotNull
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
}