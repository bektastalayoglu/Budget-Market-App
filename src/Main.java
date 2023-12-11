import Classes.BudgetMarket;
import Classes.Client;
import Classes.Product;
import Classes.Store;
import Interfaces.iBudgetMarket;

public class Main {
    public static void main(String[] args) {

        // Create BudgetMarket Application
        iBudgetMarket marketApp = new BudgetMarket();

        // Add clients, returns id of clients
        int client1 = marketApp.addClient("Bektas Talayoglu", "bektas.talayoglu@vub.be", "Dautzenberg Street");
        int client2 = marketApp.addClient("Yusuf Karadeniz", "yusuf@gmail.com", "Wall Street");
        int client3 = marketApp.addClient("Burak Gul", "burak@gmail.com", "Lombard Street");


        // Add stores, returns id of store
        int store1 = marketApp.addStore("Store A ", "Flagey Street");
        int store2 = marketApp.addStore("Store B", "Main Street");
        int store3 = marketApp.addStore("Store C", "White Street");


        // Add products, returns id of products
        int product1 = marketApp.addProduct("Banana", 1.50f, store1);
        int product2 = marketApp.addProduct("Apples", 0.70f, store2);
        int product3 = marketApp.addProduct("Chicken", 4.50f, store2);
        int product4 = marketApp.addProduct("Ham", 2.50f, store3);


        // I tested my code...
        System.out.println("----------------------------------");

        Client appClient1 = marketApp.findClient(client1);
        Client appClient2 = marketApp.findClient(client2);

        if (appClient1 != null) {
            System.out.println(appClient1);
        } else {
            System.out.println("Sorry, client is not exist.");
        }

        // Print all clients
        System.out.println("----------------------------------");
        marketApp.printAllClients();


        System.out.println("-------------------------------");
        Store store = marketApp.findStore(2);
        if (store != null) {
            System.out.println(store);
        } else {
            System.out.println("Sorry, store doesnt exist...");
        }

        // Call print all store
        System.out.println("----------------------------------");
        marketApp.printAllStores();

        System.out.println("----------------------------------");

        Product appProduct1 = marketApp.findProduct(product1);
        Product appProduct2 = marketApp.findProduct(product2);

        if (appProduct2 != null) {
            System.out.println(appProduct1);
        } else {
            System.out.println("Sorry, product is not exist.");
        }

        // Print all products
        System.out.println("----------------------------------");
        marketApp.printAllProducts();

        System.out.println(marketApp.addProductToShoppingList(client1, "Banana"));
        System.out.println(marketApp.addProductToShoppingList(client1, "Apples"));
        System.out.println(marketApp.addProductToShoppingList(client1, "Chicken"));
        System.out.println(marketApp.addProductToShoppingList(client1, "Ham"));
        System.out.println(marketApp.buyProducts(client1));


    }
}
