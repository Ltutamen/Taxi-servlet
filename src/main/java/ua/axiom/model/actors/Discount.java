package ua.axiom.model.actors;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class Discount {

    public enum DiscountType{B_DAY, RANDOM;}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Client client;

    private float multiplier;

    private DiscountType type;

    public Discount(Client client, float v, DiscountType random) {
        this.client = client;
        this.multiplier = v;
        this.type = random;
    }
}
