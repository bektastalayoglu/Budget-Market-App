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
        int client1 = marketApp.addClient("Bektas Talayoglu", "bektas.talayoglu@vub.be", "Street A");
        int client2 = marketApp.addClient("Yusuf Karadeniz", "yusuf@gmail.com", "Street B");
        int client3 = marketApp.addClient("Burak Gul", "burak@gmail.com", "Street C");


        // Add stores, returns id of store
        int store1 = marketApp.addStore("Store A ", "Street A");
        int store2 = marketApp.addStore("Store B", "Street B");
        int store3 = marketApp.addStore("Store C", "Street C");


        // Add products, returns id of products
        int product1 = marketApp.addProduct("Ham", 160.50f, store3);
        int product4 = marketApp.addProduct("Ham", 24.70f, store2);
        // int product6 = marketApp.addProduct("Ham", 10.50f, store1);
        int product5 = marketApp.addProduct("Beef", 10.50f, store1);



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

        if (appProduct1 != null) {
            System.out.println(appProduct1);
        } else {
            System.out.println("Sorry, product is not exist.");
        }

        // Print all products
        System.out.println("----------------------------------");
        marketApp.printAllProducts();


        System.out.println(marketApp.addProductToShoppingList(client1, "Ham"));
        //System.out.println(marketApp.addProductToShoppingList(client1, "Beef"));

        marketApp.printAllClients();
        System.out.println(marketApp.buyProducts(client1));

        System.out.println(marketApp.removeProductFromStore("Ham", store2));
        marketApp.addStreet("Street A");
        marketApp.addStreet("Street B");
        marketApp.addStreet("Street C");

        marketApp.connectStreets("Street A", "Street B", 5);
        marketApp.connectStreets("Street B", "Street C", 8);


        System.out.println(marketApp.getShoppingDirections(client1,1));

    }
}
