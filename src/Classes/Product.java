package Classes;

public class Product {

    private int productID;
    private String category;
    private float price;

    // storeID - To represent the store where the product is available
    private int storeID;

    /*
     * Constructor for creating a Product object with a unique ID, category, price.
     * productIDCount is a mechanism to automatically generate unique IDs.
     * @param category - Category of the product
     * @param price - Price of the product
     */
    public Product(String category, Float price, int productID) {
        this.productID = productID;
        this.category = category;
        this.price = price;
    }

    /*
     * Get the unique ID of the product.
     * @return The product ID
     */
    public int getProductID() {
        return productID;
    }


    /*
     * Get the category of the product.
     * @return The product category
     */
    public String getCategory() {
        return category;
    }


    /*
     * Get the price of the product.
     * @return The product price
     */
    public float getPrice() {
        return price;
    }


    /*
     * Get the ID of the store where the product is available.
     * @return The store ID
     */
    public int getStoreID() {
        return storeID;
    }

    /*
     * Set the ID of the store where the product is available.
     * @param storeID - New store ID
     */
    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    /*
     * Generate a string representation of the product.
     * @return A string containing product ID, category, and price
     */
    @Override
    public String toString() {
        return productID + ", " + category + ", " + price;
    }


}
