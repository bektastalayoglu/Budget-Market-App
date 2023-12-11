package Classes;

import Algorithms.Stack;
import Algorithms.Vector;

public class Client {

    private static int clientIDCount = 1;
    private int clientID = 0;
    private String name;

    private String email;

    private String address;

    private Vector<String> shoppingList;

    /*
     * Constructor for creating a Client object with a unique ID, name, email, address,
     * and an empty shopping list.
     * clientIDCount is a mechanism to automatically generate unique IDs.
     * @param clientName - Name of the client
     * @param email - Email of the client
     * @param clientAddress - Address of the client
     */
    public Client(String name, String email, String address) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.shoppingList = new Vector<>();
        this.clientID = clientIDCount++;  // Use the instance to generate a unique ID
    }


    /*
     * Get the unique ID of the client.
     * @return The client ID
     */
    public int getClientId() {
        return clientID;
    }

    /*
     * Set a new ID for the client.
     * @param clientID - New client ID
     */
    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    /*
     * Get the name of the client.
     * @return The client name
     */
    public String getClientName() {
        return name;
    }

    /*
     * Set a new name for the client.
     * @param clientName - New client name
     */
    public void setClientName(String clientName) {
        this.name = clientName;
    }

    /*
     * Get the email of the client.
     * @return The client email
     */
    public String getEmail() {
        return email;
    }

    /*
     * Set a new email for the client.
     * @param email - New client email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /*
     * Get the address of the client.
     * @return The client address
     */
    public String getClientAddress() {
        return address;
    }

    /*
     * Set a new address for the client.
     * @param clientAddress - New client address
     */
    public void setClientAddress(String clientAddress) {
        this.address = clientAddress;
    }

    /*
     * Get the shopping list of the client.
     * @return The client's shopping list
     */
    public Vector<String> getShoppingList() {
        return shoppingList;
    }

    /*
     * Generate a string representation of the client.
     * @return A string containing client ID, name, email, and address
     */
    @Override
    public String toString() {
        return clientID + ", " + name + ", " + email + ", " + address;
    }

}
