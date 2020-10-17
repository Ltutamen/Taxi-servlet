package ua.axiom.model.actors;

import javax.persistence.*;

@Entity
public class Discount {

    public enum DiscountType{B_DAY, RANDOM;}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Client client;

    private float multiplier;

    @Embedded
    private DiscountType type;

    public Discount(long id, Client client, float v, DiscountType random) {
        this.id = id;
        this.client = client;
        this.multiplier = v;
        this.type = random;
    }

    public Discount() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
