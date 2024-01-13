package Classes;

import Algorithms.Dictionary.Dictionary;
import Algorithms.PriorityQ.PriorityQueue;
import Algorithms.Vector.Vector;
import Algorithms.Graph.Graph;
import Interfaces.iBudgetMarket;

/**
 * @author : Bektas Talayoglu
 * Description : Budget Market class is used manage market app.
 **/


public class BudgetMarket implements iBudgetMarket {
    private Dictionary<Integer, Store> stores;
    private Dictionary<Integer, Product> products;
    private Dictionary<Integer, Client> clients;

    private Dictionary<String, PriorityQueue<Store, Float>> productInStore;

    private Graph graph;

    private int clientID = 1;
    private int storeID = 1;
    private int productID = 1;

    public BudgetMarket() {
        this.stores = new Dictionary<>();
        this.products = new Dictionary<>();
        this.clients = new Dictionary<>();
        this.productInStore = new Dictionary<>();
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
  /*  @Override
    public int addProduct(String category, Float price, int storeID) {
        // Find the store according to its store ID
        Store store = findStore(storeID);

        // Find stores with the specified category and their corresponding prices.
        PriorityQueue<Store, Float> categoryQ = productInStore.find(category);

        // If the product category already exists, push store and price of product to priorityQ
        if (categoryQ != null) {
            int productID = findProductID(category);
            if (productID != -1) {
                categoryQ.push(store, price);
                return productID;
            }
        }

        // If the category not exist we create new product
        Product newProduct = new Product(category, price, productID++);

        // Maps product id and product and set the store
        products.add(newProduct.getProductID(), newProduct);
        //newProduct.setStoreID(storeID);

        // If category does not exist

        categoryQ = new PriorityQueue<>();
        productInStore.add(category, categoryQ);
        categoryQ.push(store, price);

        return newProduct.getProductID();
    }*/
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

    // Helper method to find a product based on its category
    private Product findProductByCategory(String productCategory) {
        for (int i = 0; i < products.size(); i++) {
            Product currentProduct = products.values().get(i);
            if (currentProduct != null && currentProduct.getCategory().equals(productCategory)) {
                return currentProduct;
            }
        }
        return null;
    }

    /**
     * Helper method to find to product ID from its category
     *
     * @param productCategory : category of the product
     * @return product id
     */
    private int findProductID(String productCategory) {

        for (int i = 0; i < products.size(); i++) {
            Product currentProduct = products.find(products.keys().get(i));
            if (currentProduct != null && currentProduct.getCategory().equals(productCategory)) {
                return currentProduct.getProductID();
            }
        }
        return -1;
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
        for (int i = 0; i < stores.size(); i++) {
            System.out.println(stores.values().get(i));
        }
    }

    /*
     * Print all products in the following format: "product ID, category, price, "
     */
    @Override
    public void printAllProducts() {
        for (int i = 0; i < products.size(); i++) {
            System.out.println(products.values().get(i));
        }

    }

    /*
     * Print all clients in the following format:
     * "client ID, name, email, address, shopping list"
     *
     */
    @Override
    public void printAllClients() {
        for (int i = 0; i < clients.size(); i++) {
            System.out.println(clients.values().get(i));
            System.out.println("Shopping list: ");
            System.out.println(clients.values().get(i).getShoppingList());
        }
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
        if (findClient(clientID) != null) {
            for (int i = 0; i < products.size(); i++) {
                if (products.values().get(i).getCategory().equals(productCategory)) {
                    findClient(clientID).getShoppingList().addLast(productCategory);
                    return true;
                }
            }
        }
        return false;
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

        if (findClient(clientID) != null) {
            Vector<String> currentShopList = findClient(clientID).getShoppingList();

            for (int i = 0; i < currentShopList.size(); i++) {
                String currentProductCategory = currentShopList.get(i);
                PriorityQueue<Store, Float> priorityQueue = productInStore.find(currentProductCategory);
                Store cheapestStore = priorityQueue.top();
                if (storesToVisit.contains(cheapestStore)) {
                    return storesToVisit;
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
        // Iterate through products to find a match by category and store ID
        Store store = findStore(storeID);
        PriorityQueue<Store, Float> categoryQ = productInStore.find(category);

        if(categoryQ != null){
            for (int i = 0; i < productInStore.values().size(); i++){
                if(categoryQ.get(i).equals(store)){
                    categoryQ.remove(i);
                    return true;
                }
            }
        }
        System.out.println("Product " + category + " not found in store " + storeID);
        return false;
    }

/*
    @Override
    public boolean removeProductFromStore(String category, int storeID) {
        // Iterate through products to find a match by category and store ID
        for (int i = 0; i < products.size(); i++) {
            Product currentProduct = products.find(i);
            PriorityQueue<Store, Float> categoryQ = productInStore.find(category);
            if (currentProduct != null && currentProduct.getCategory().equals(category) && currentProduct.getStoreID() == storeID) {
                // Remove the product from the products dictionary
                products.removeKey(currentProduct.getProductID());

                System.out.println("Product " + category + " removed from store " + storeID);
                return true;
            }
        }
        System.out.println("Product " + category + " not found in store " + storeID);
        return false;
    }
*/

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
/*        graph.findPath(street1, street2);
        System.out.println(graph);*/
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
            String homeStreet = client.getClientAddress();

            // Perform BFS to find stores within the specified distance
            Vector<Graph.Node> nodesWithinDistance = graph.getNodesWithinDistance(homeStreet, distance);

            for (int i = 0; i < nodesWithinDistance.size(); i++) {
                Graph.Node node = nodesWithinDistance.get(i);

                // Find the store corresponding to the street
                Store store = findStoreByStreet((String) node.getLabel());

                if (store != null && hasAllProducts(store, client.getShoppingList())) {
                    storesToVisit.addLast(store);
                }
            }
        }

        return storesToVisit;
    }

    // Helper method to check if a store has all the products from the shopping list
    private boolean hasAllProducts(Store store, Vector<String> shoppingList) {
        for (int i = 0; i < shoppingList.size(); i++) {
            String productCategory = shoppingList.get(i);
            PriorityQueue<Store, Float> priorityQueue = productInStore.find(productCategory);

            if (priorityQueue != null) {
                Store cheapestStore = priorityQueue.top();
                if (!store.equals(cheapestStore)) {
                    // The store doesn't have the required product
                    return false;
                }
            } else {
                // The product category is not available in any store
                return false;
            }
        }
        return true;
    }


    // Helper method to find a store based on its street
    private Store findStoreByStreet(String street) {
        for (int i = 0; i < stores.size(); i++) {
            Store currentStore = stores.values().get(i);
            if (currentStore != null && currentStore.getStoreAddress().equals(street)) {
                return currentStore;
            }
        }
        return null;
    }
    /************************** end of PART 3 ***************************/
}
