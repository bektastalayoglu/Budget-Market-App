/**
 * Author : Bektas Talayoglu
 * Description : Client class is used manage clients in the app.
 **/

package Classes;

import Algorithms.Vector.Vector;

public class Client {

    // private static int clientIDCount = 1;
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
    public Client(String name, String email, String address, int clientID) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.shoppingList = new Vector<>();
        this.clientID = clientID;
    }


    /*
     * Get the unique ID of the client.
     * @return The client ID
     */
    public int getClientId() {
        return clientID;
    }

    /*
     * Get the name of the client.
     * @return The client name
     */
    public String getClientName() {
        return name;
    }


    /*
     * Get the email of the client.
     * @return The client email
     */
    public String getEmail() {
        return email;
    }


    /*
     * Get the address of the client.
     * @return The client address
     */
    public String getClientAddress() {
        return address;
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
