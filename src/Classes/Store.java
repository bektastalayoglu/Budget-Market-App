package Classes;

import Algorithms.PriorityQueue;
import Algorithms.Vector;

public class Store {

    private int storeID;
    private String name;
    private String address;



    /*
     * Constructor for creating a Store object with a unique ID, name, and address.
     * @param storeName - Name of the store
     * @param storeAddress - Address of the store
     */
    public Store(String storeName, String storeAddress, int storeID) {
        this.storeID = storeID;
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
     * Get the address of the store.
     * @return The store address
     */
    public String getStoreAddress() {
        return address;
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
