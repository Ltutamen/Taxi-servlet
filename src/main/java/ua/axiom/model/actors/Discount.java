package ua.axiom.model.actors;

import ua.axiom.persistance.Persistent;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class Discount extends Persistent<Long> {

    public enum DiscountType{B_DAY, RANDOM;}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Client client;

    private float multiplier;

    private DiscountType type;

    public Discount(long id, Client client, float v, DiscountType random) {
        super(id);
        this.client = client;
        this.multiplier = v;
        this.type = random;
    }
}
