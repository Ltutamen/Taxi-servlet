package ua.axiom.model.actors;

import ua.axiom.persistance.jdbcbased.Persistent;

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

    private String modelName;

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