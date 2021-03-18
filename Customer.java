/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.eap.plh24.ask2020_2_3.model;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alefrag
 */
public class Customer {
    private long AFM;
    private String name;
    private String address;
    private List<Order> orders = new ArrayList<>();

    public Customer(long AFM, String name, String address) {
        this.AFM = AFM;
        this.name = name;
        this.address = address;
    }

    public long getAFM() {
        return AFM;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public final List<Order> getOrders() {
        return orders;
    }
    
    public void addOrder(Order o){
        orders.add(o);
    }

    @Override
    public String toString() {
        return "Customer{" + "AFM=" + AFM + ", name=" + name + '}';
    }    
}
