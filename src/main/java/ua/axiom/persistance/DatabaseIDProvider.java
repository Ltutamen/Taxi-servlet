package ua.axiom.persistance;


//  todo remove
public class DatabaseIDProvider {
    private static long previousID;

    public synchronized long getID() {
        return ++previousID;
    }
}
