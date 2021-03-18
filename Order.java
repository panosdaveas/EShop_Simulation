/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.eap.plh24.ask2020_2_3.model;

import static gr.eap.plh24.ask2020_2_3.model.Order.OrderState.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author alefrag
 */
public class Order implements Comparable<Order>{
    
    enum OrderState { SUBMITTED , PROCESSED, FINISHED, CANCELLED};
    
    private long id;
    private Customer customer;
    private HashMap<Product,Integer> products;
        
    private OrderState state = SUBMITTED;
    private LocalDateTime orderTime;
    private LocalDateTime dispatchDate;
    private DeliveryMethod DispatchType; 
    private LocalDateTime deliveryDate;
    private String packagerId;

    public Order(Customer customer, HashMap<Product, Integer> products, LocalDateTime orderDate) {
        this.customer = customer;
        this.products = products;
        this.orderTime = orderDate;
        this.id = orderDate.toEpochSecond(ZoneOffset.UTC);
        customer.addOrder(this);
    }

    public long getId() {
        return id;
    }
    
    private boolean isComplete(){
        return state == FINISHED;
    }
    
    public float getWeight(){
        float sum = 0.0f;
        for(Entry<Product,Integer> p:products.entrySet()){
            sum+=p.getKey().getWeight()*p.getValue();
        }
        return sum;
    }
    
    public float getTotal(){
        float sum = 0.0f;
        for(Entry<Product,Integer> p:products.entrySet()){
            sum+=p.getKey().getPrice(this)*p.getValue();
        }
        //extra Line//
        //αποκοπή του δεκαδικού μέρους μετά τα δυο πρώτα ψηφία
        sum = (float) ((int)(sum * 100f)) /100f;
        //////////////
        return sum;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }


    public DeliveryMethod getDispatchType() {
        return DispatchType;
    }

    public void setDispatchType(DeliveryMethod DispatchType) {
        this.DispatchType = DispatchType;
    }

    public LocalDateTime getDispatchDate() {
        return dispatchDate;
    }

    public void setDispatchDate(LocalDateTime dispatchDate) {
        this.dispatchDate = dispatchDate;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }  

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    //extra Methods//
    public void setPackagerId(int packagerId){ this.packagerId = new String("r"+ packagerId);}

    public String getPackagerId() { return packagerId;}
    ////////////////
    ///////////////
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Order " + "id=" + id + ", customer=" + customer +"\n");
        for(Entry<Product, Integer> p:products.entrySet()){
            sb.append(p.getKey() + "\t" + p.getValue() + "\n");
        }
        int len = "DispatchDate".toString().length();
        sb.append("state="          + String.format("%" + (20 + 7 - len + "s"), "") + state +
               "\norderDate="       + String.format("%" + (20 + 3 - len + "s"), "") + orderTime +
               "\nDispatchDate="    + String.format("%" + (20 - len + "s"), "") + dispatchDate +
               "\nDeliveryDate="    + String.format("%" + (20 - len + "s"), "") + deliveryDate +
                "\nDeliveryType="    + String.format("%" + (20 - len + "s"), "") + DispatchType.getName() +
               "\nPrice="           + String.format("%" + (20 + 7 - len + "s"), "") + getTotal() +
               "\nDeliveryCharges=" + String.format("%" + (20 - 3 - len + "s"), "") + getDispatchType().getCost(this) +
               "\nPackagerId="      + String.format("%" + (20 + 2 - len + "s"), "") + packagerId + '\n');
       return sb.toString();
    }

    @Override
    public int compareTo(Order o) {
        return orderTime.compareTo(o.getOrderTime());
    }
    
    
       
}
