package ua.axiom.persistance;

public class DatabaseIDProvider {
    private static long previousID;

    public synchronized long getID() {
        return ++previousID;
    }
}
