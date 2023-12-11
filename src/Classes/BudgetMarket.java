package Classes;

import Algorithms.Vector;
import Interfaces.iBudgetMarket;

public class BudgetMarket implements iBudgetMarket {
    private Vector<Store> stores;
    private Vector<Product> products;
    private Vector<Client> clients;

    public BudgetMarket() {
        this.stores = new Vector<>();
        this.products = new Vector<>();
        this.clients = new Vector<>();
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
        Store newStore = new Store(name, street);
        stores.addLast(newStore);
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
        // When we add product we should also check the store whether exist or not...
        // If the store exists we updated product store ID
        Product newProduct = new Product(category, price);
        products.addLast(newProduct);
        if (findStore(storeID) != null) {
            System.out.println(newProduct.getCategory() + " added...");
            newProduct.setStoreID(storeID);
            return newProduct.getProductID();
        } else {
            System.out.println("Store does not exist");
            return -1;
        }
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
        Client newClient = new Client(name, email, street);
        clients.addLast(newClient);
        return newClient.getClientId();
    }

    /*
     * Print all stores in the following format:
     * "store ID, store name, store address"
     */
    @Override
    public void printAllStores() {
        for (int i = 0; i < stores.size(); i++) {
            System.out.println(stores.get(i));
        }
    }

    /*
     * Print all products in the following format: "product ID, category, price, "
     */
    @Override
    public void printAllProducts() {
        for (int i = 0; i < products.size(); i++) {
            System.out.println(products.get(i));
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
            System.out.println(clients.get(i));
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
        for (int i = 0; i < stores.size(); i++) {
            Store store = stores.get(i);

            if (storeID == store.getStoreID()) {
                return store;
            }
        }
        return null;
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
        for (int i = 0; i < products.size(); i++) {

            Product product = products.get(i);
            if (productID == product.getProductID()) {
                return product;
            }
        }
        return null;
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
        for (int i = 0; i < clients.size(); i++) {
            Client client = clients.get(i);

            if (clientID == client.getClientId()) {
                return client;
            }
        }
        return null;
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
                if (products.get(i).getCategory().equals(productCategory)) {
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

                // Iterate through products to find a match by category
                for (int j = 0; j < products.size(); j++) {
                    if (products.get(j).getCategory().equals(currentProductCategory)) {
                        Store store = findStore(products.get(i).getStoreID());

                        if (store != null && !storesToVisit.contains(store)) {
                            storesToVisit.addLast(store);
                        }
                        break;  // Break the inner loop once a match is found
                    }
                }
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
