package Classes;

/**
 * @author : Bektas Talayoglu
 * Description : Product class is used manage product in the app.
 **/

public class Product {
    private int productID;
    private String category;
    private float price;

    /*
     * Constructor for creating a Product object with a unique ID, category, price.
     * @param category - Category of the product
     * @param price - Price of the product
     */
    public Product(String category, Float price, int productID) {
        this.productID = productID;
        this.category = category;
        this.price = price;
    }


    /**
     * Get the price of the product.
     *
     * @return The price of product
     **/
    public float getPrice() {
        return price;
    }

    /**
     * Get the unique ID of the product.
     *
     * @return The product ID
     **/
    public int getProductID() {
        return productID;
    }

    /**
     * Get the category of the product.
     *
     * @return The product category
     **/
    public String getCategory() {
        return category;
    }

    /**
     * Generate a string representation of the product.
     *
     * @return A string containing product ID, category, and price
     **/
    @Override
    public String toString() {
        return productID + ", " + category + ", " + price;
    }


}
