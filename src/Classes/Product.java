package Classes;

public class Product {

    private int productID;
    private static int productIDCount = 1;
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
    public Product(String category, Float price) {
        this.productID = productIDCount++;
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
     * Set a new ID for the product.
     * @param productID - New product ID
     */
    public void setProductID(int productID) {
        this.productID = productID;
    }

    /*
     * Get the category of the product.
     * @return The product category
     */
    public String getCategory() {
        return category;
    }

    /*
     * Set a new category for the product.
     * @param category - New product category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /*
     * Get the price of the product.
     * @return The product price
     */
    public double getPrice() {
        return price;
    }

    /*
     * Set a new price for the product.
     * @param price - New product price
     */
    public void setPrice(float price) {
        this.price = price;
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
