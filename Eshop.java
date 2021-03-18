/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.eap.plh24.ask2020_2_3.model;

import java.time.LocalDateTime;
import java.util.*;

/**
 *
 * @author alefrag
 */
public class Eshop {
    private Warehouse warehouse;
    private List<Product> products ;
    private List<Category> categories;
    
    private Map<Long,Order> orders = new TreeMap<>();
    private Map<Long,Customer> customers = new HashMap<>();

    public Eshop(Warehouse warehouse, List<Product> products, List<Category> categories) {
        this.warehouse = warehouse;
        this.products = products;
        this.categories = categories;
    }    

    public Map<Long, Order> getOrders() {
        return orders;
    }
    
    public void addOrder(Order o){
        orders.put(o.getId(), o);
        warehouse.addOrder(o);
    } 

    public void addCustomer(Customer c) {
        customers.put(c.getAFM(), c);
    }
    
    public Iterable<Product> getProducts() {
        return products;
    }

    public void restockProducts(Map<SimpleProduct, Integer> newStock, LocalDateTime restockTime) {
        warehouse.restockProducts(newStock,restockTime);
    }

    public Collection<Customer> getCustomers() {
        return customers.values();
    }

    public void printPendingOrders() {
        warehouse.printPendingOrders();
    }

    public SortedSet<Order> getPendingOrders() {
        return warehouse.getPendingOrders();
    }
        
    public void printAllOrders() {
        System.out.println("Συνολικές παραγγελίες " + orders.size() + "\n");
        for(Order o : orders.values()){
                System.out.println(o);
        }
    }

    public Iterable<Map.Entry<SimpleProduct,Integer>> getStock() {
        return warehouse.getStock();
    }

    //extra Methods//
    public void printCancelledOrders() {
        warehouse.printCancelledOrders();
    }

    public  void printInventory(){
        System.out.println("======================" + "\n");
        for(Map.Entry<SimpleProduct, Integer> entry : getStock()){
            System.out.println(entry.getKey().getName() + String.format("%" + (20 - entry.getKey().getName().length()) + "s", "")+ entry.getValue() + "\n");
        }
        System.out.println("======================" + "\n");
    }
    ////////////////

}
