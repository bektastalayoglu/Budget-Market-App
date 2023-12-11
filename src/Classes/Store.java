package Classes;

public class Store {

    private int storeID;
    private static int storeIDCount = 1;
    private String name;
    private String address;

    /*
     * Constructor for creating a Store object with a unique ID, name, and address.
     * storeIDCount is a mechanism to automatically generate unique IDs.
     * @param storeName - Name of the store
     * @param storeAddress - Address of the store
     */
    public Store(String storeName, String storeAddress) {
        this.storeID = storeIDCount++;
        this.name = storeName;
        this.address = storeAddress;
    }

    /*
     * Get the unique ID of the store.
     * @return The store ID
     */
    public int getStoreID() {
        return storeID;
    }

    /*
     * Set a new ID for the store.
     * @param storeID - New store ID
     */
    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    /*
     * Get the name of the store.
     * @return The store name
     */
    public String getStoreName() {
        return name;
    }

    /*
     * Set a new name for the store.
     * @param storeName - New store name
     */
    public void setStoreName(String name) {
        this.name = name;
    }

    /*
     * Get the address of the store.
     * @return The store address
     */
    public String getStoreAddress() {
        return address;
    }

    /*
     * Set a new address for the store.
     * @param storeAddress - New store address
     */
    public void setStoreAddress(String address) {
        this.address = address;
    }


    /*
     * Generate a string representation of the store.
     * @return A string containing store ID, name, and address
     */
    @Override
    public String toString() {
        return storeID + ", " + name + ", " + address;
    }
}
