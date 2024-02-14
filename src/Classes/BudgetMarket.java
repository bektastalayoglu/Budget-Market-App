package Classes;

import Algorithms.Dictionary.Dictionary;
import Algorithms.Dictionary.DictionaryTree;
import Algorithms.PriorityQ.PriorityQueue;
import Algorithms.Vector.Vector;
import Algorithms.Graph.Graph;
import Interfaces.iBudgetMarket;

/**
 * @author : Bektas Talayoglu
 * Description : Budget Market class is used manage market app.
 **/


public class BudgetMarket implements iBudgetMarket {
    private DictionaryTree<Integer, Store> stores;
    private DictionaryTree<Integer, Product> products;
    private DictionaryTree<Integer, Client> clients;

    private DictionaryTree<String, PriorityQueue<Store, Float>> productInStore;

    private Graph graph;

    private int clientID = 1;
    private int storeID = 1;
    private int productID = 1;

    public BudgetMarket() {
        this.stores = new DictionaryTree<>();
        this.products = new DictionaryTree<>();
        this.clients = new DictionaryTree<>();
        this.productInStore = new DictionaryTree<>();
        this.graph = new Graph();
    }

    /************************** PART 1 ***************************/
    /*
     * Add a new store with given parameters: store's name and name of the street.
     *
     * @param name of the store
     *
     * @param street address
     *
     * @return ID of the store
     */
    @Override
    public int addStore(String name, String street) {
        Store newStore = new Store(name, street, storeID++);
        stores.add(newStore.getStoreID(), newStore);
        return newStore.getStoreID();
    }

    /*
     * Add a product with given parameters: category, price, and store in which this
     * product is available.
     *
     * @param category of the product, e.g. apple, orange, chicken, ham, etc.
     *
     * @param price
     *
     * @param storeID where this product is available
     *
     * @return ID of the product
     */
    @Override
    public int addProduct(String category, Float price, int storeID) {
        // Find the store according to its store ID
        Store store = findStore(storeID);

        // If the store does not exist return -1
        if (store == null) {
            System.out.println("Store does not exist...");
            return -1;
        }

        // Finds stores where the category is available
        PriorityQueue<Store, Float> stores = productInStore.find(category);
        // If the product category does not exist, create and add the dictionary
        if (stores == null) {
            stores = new PriorityQueue<>();
            productInStore.add(category, stores);
        }

        // Create a new product and increment its ID
        Product newProduct = new Product(category, price, productID++);

        // adds product to the dictionary and maps it with its ID
        products.add(newProduct.getProductID(), newProduct);

        // Push store and price of the product to the PriorityQueue
        stores.push(store, price);

        return newProduct.getProductID();
    }

    /*
     * Add a new client with given parameters: name, email address, and street
     * address.
     *
     * @param client's name
     *
     * @param client's email address
     *
     * @param client's street address
     *
     * @return ID of the Classes.Client
     */
    @Override
    public int addClient(String name, String email, String street) {
        Client newClient = new Client(name, email, street, clientID++);
        clients.add(newClient.getClientId(), newClient);
        return newClient.getClientId();
    }

    /*
     * Print all stores in the following format:
     * "store ID, store name, store address"
     */
    @Override
    public void printAllStores() {
        System.out.println(stores);
    }

    /*
     * Print all products in the following format: "product ID, category, price, "
     */
    @Override
    public void printAllProducts() {
        System.out.println(products);
    }

    /*
     * Print all clients in the following format:
     * "client ID, name, email, address, shopping list"
     *
     */
    @Override
    public void printAllClients() {
        System.out.println(clients);
    }

    /*
     * Return store based on storeID or null if store does not exist.
     *
     * @param storeID
     *
     * @return Classes.Store object
     */
    @Override
    public Store findStore(int storeID) {
        return stores.find(storeID);
    }

    /*
     * Return product based on productID or null if product does not exist.
     *
     * @param productID
     *
     * @return Classes.Product object
     */
    @Override
    public Product findProduct(int productID) {
        return products.find(productID);
    }

    /*
     * Return client based on clientID or null if client does not exist.
     *
     * @param clientID
     *
     * @return Classes.Client object
     */
    @Override
    public Client findClient(int clientID) {
        return clients.find(clientID);
    }

    /************************** end of PART 1 ***************************/

    /************************** PART 2 ***************************/

    /*
     * Add product to client's shopping list.
     *
     * @param client id
     *
     * @param product category
     *
     * @return true if successful, false otherwise
     */
    @Override
    public boolean addProductToShoppingList(int clientID, String productCategory) {
        // Check client id
        Client client = findClient(clientID);
        if (client != null) {
            // Traverse all products until you find the product the client wants to add to their shopping list
            for (int i = 0; i < products.size(); i++) {
                if (products.values().get(i).getCategory().equals(productCategory)) {
                    client.getShoppingList().addLast(productCategory);
                    // product successfully added to shopping list
                    return true;
                }
            }
        }
        // Product Category does not exist
        return false;
    }

/*    public boolean addProductToShoppingList(int clientID, String productCategory){
        if(findProductByCategory(productCategory) != null && findClient(clientID) != null){
            findClient(clientID).getShoppingList().addLast(productCategory);
            return true;
        }
        return false;
    }*/

    private Product findProductByCategory(String productCategory) {
        for (int i = 0; i < products.size(); i++) {
            Product currentProduct = products.find(i);
            if (currentProduct != null && currentProduct.getCategory().equals(productCategory)) {
                return currentProduct;
            }
        }
        return null;
    }


    /*
     * Return stores the client needs to visit to buy (ideally all) products from
     * their shopping list.
     *
     * @param client ID
     *
     * @return Vector of all stores the client needs to visit.
     */
    @Override
    public Vector<Store> buyProducts(int clientID) {
        Vector<Store> storesToVisit = new Vector<>();
        Client client = findClient(clientID);
        // Check client id
        if (client != null) {
            // Get current shopping list of the client
            Vector<String> currentShoppingList = client.getShoppingList();

            // According to products in client shopping list and return cheapest stores
            for (int i = 0; i < currentShoppingList.size(); i++) {
                String currentProductCategory = currentShoppingList.get(i);
                PriorityQueue<Store, Float> priorityQueue = productInStore.find(currentProductCategory);
                Store cheapestStore = priorityQueue.top();
                if (storesToVisit.contains(cheapestStore)) {
                    continue;
                }
                storesToVisit.addLast(cheapestStore);
            }
        }
        return storesToVisit;
    }

    /*
     * Remove product offered in the store.
     *
     * @param product category
     *
     * @param store ID
     *
     * @return true if successful, false otherwise
     */

    @Override
    public boolean removeProductFromStore(String category, int storeID) {
        // Find product according to its store ID
        Store store = findStore(storeID);
        PriorityQueue<Store, Float> stores = productInStore.find(category);

        // If stores exist find the store and remove product from the store
        if (stores != null) {
            for (int i = 0; i < stores.size(); i++) {
                if (stores.get(i).equals(store)) {
                    stores.remove(i);
                    return true;
                }
            }
        }
        System.out.println("Product " + category + " not found in store " + storeID);
        return false;
    }

    /************************** end of PART 2 ***************************/

    /************************** PART 3 ***************************/

    /*
     * Add a street.
     *
     * @param name of the street
     */
    @Override
    public void addStreet(String street) {
        graph.addNode(street);
    }

    /*
     * Connects two streets.
     *
     * @param street1
     *
     * @param street2
     *
     * @param distance
     */
    @Override
    public void connectStreets(String street1, String street2, int distance) {
        graph.addEdge(street1, street2, distance);
    }

    /*
     * Return stores, within given distance, the client needs to visit to buy
     * products from their shopping list.
     *
     * @param clientID
     *
     * @param distance
     *
     * @return Vector of stores to visit
     *
     */
    @Override
    public Vector<Store> getShoppingDirections(int clientID, int distance) {
        Vector<Store> storesToVisit = new Vector<>();

        // Find the client based on clientID
        Client client = findClient(clientID);

        if (client != null) {
            // Reset the visited status of the graph nodes
            graph.resetVisitedStatus();

            // Get the home street of the client
            String homeStreet = client.getAddress();

            // Perform BFS to find stores within the specified distance
            Vector<String> nodesWithinDistance = graph.getNodesWithinDistance(homeStreet, distance);

            Vector<String> currentShoppingList = findClient(clientID).getShoppingList();
            // Iterate through each product in the client's shopping list
            for (int i = 0; i < currentShoppingList.size(); i++) {
                String currentProductCategory = currentShoppingList.get(i);
                // Find the PriorityQueue for the product category
                PriorityQueue<Store, Float> priorityQueueCopy = productInStore.find(currentProductCategory).copy();

                if (priorityQueueCopy != null) {
                    // Iterate through stores in PriorityQueue until a store within distance is found
                    while (!priorityQueueCopy.isEmpty()) {
                        Store store = priorityQueueCopy.top();
                        if (store != null) {
                            priorityQueueCopy.pop();
                            if (nodesWithinDistance.contains(store.getStoreAddress())) {
                                // Check if the store has any product from the shopping list
                                if (storesToVisit.contains(store)) {
                                    break;
                                }
                                storesToVisit.addLast(store);
                                break; // Move to the next product after finding a suitable store
                            }
                        }
                    }
                }
            }
        }
        return storesToVisit.isEmpty() ? null : storesToVisit;
    }

    /************************** end of PART 3 ***************************/
}
