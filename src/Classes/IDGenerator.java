package Classes;

public class IDGenerator {
    private int count;

    public IDGenerator() {
        this.count = 1; // Initialize count to 1
    }

    public int generateID() {
        return count++;
    }
}

