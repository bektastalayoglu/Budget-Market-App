package Classes;

import Algorithms.Dictionary;
import Algorithms.PriorityQueue;
import Algorithms.Vector;
import Interfaces.iBudgetMarket;

public class BudgetMarket implements iBudgetMarket {
    private Dictionary<Integer, Store > stores;
    private Dictionary<Integer, Product> products;
    private Dictionary<Integer, Client> clients;

    private Dictionary<String, PriorityQueue<Store, Float>> dd;

    private int clientID = 1;
    private int storeID = 1;
    private int productID = 1;

    public BudgetMarket() {
        this.stores = new Dictionary<>();
        this.products = new Dictionary<>();
        this.clients = new Dictionary<>();
        this.dd = new Dictionary<>();
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
        Store store = findStore(storeID);
        PriorityQueue<Store, Float> q = dd.find(category);

        if (q != null) {
            int proID = findProductID(category);
            if (proID != -1) {
                q.push(store, price);
                return proID;
            }
        }

        Product newProduct = new Product(category, price, productID++);
        products.add(newProduct.getProductID(), newProduct);
        newProduct.setStoreID(storeID);

        if (q == null) {
            q = new PriorityQueue<>();
            dd.add(category, q);
        }

        q.push(store, price);
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
        for(int i = 1; i <= stores.size(); i++){
            System.out.println(stores.find(i));
        }

    }

    /*
     * Print all products in the following format: "product ID, category, price, "
     */
    @Override
    public void printAllProducts() {
        for(int i = 1; i <= products.size(); i++){
            System.out.println(products.find(i));
        }

    }

    /*
     * Print all clients in the following format:
     * "client ID, name, email, address, shopping list"
     *
     */
    @Override
    public void printAllClients() {
        for(int i = 1; i <= clients.size(); i++){
            System.out.println(clients.find(i));
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
            for (int i = 1; i <= products.size(); i++) {
                if (products.find(i).getCategory().equals(productCategory)) {
                    findClient(clientID).getShoppingList().addLast(productCategory);
                    return true;
                }
            }
        }
        return false;
    }

    private int findProductID(String productCategory) {
        for (int i = 1; i <= products.size(); i++) {
            Product currentProduct = products.find(i);
            if (currentProduct != null && currentProduct.getCategory().equals(productCategory)) {
                return currentProduct.getProductID();
            }
        }
        return -1;
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
                PriorityQueue<Store, Float> priorityQueue = dd.find(currentProductCategory);
                Store cheapestStore = priorityQueue.top();
                if(storesToVisit.contains(cheapestStore)){
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
        for (int i = 0; i < products.size(); i++) {
            Product currentProduct = products.find(i);
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

    /************************** end of PART 2 ***************************/

    /************************** PART 3 ***************************/

    /*
     * Add a street.
     *
     * @param name of the street
     */
    @Override
    public void addStreet(String street) {

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
    public Vector getShoppingDirections(int clientID, int distance) {
        return null;
    }
    /************************** end of PART 3 ***************************/
}
