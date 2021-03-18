/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.eap.plh24.ask2020_2_3.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author alefrag
 */
public class Category {

    private String name;
    private final List<Product> products=new ArrayList<>();
    private float profitMargin;
    
    public Category(String name, float profitMargin) {
        this.name = name;
        this.profitMargin = profitMargin;
    }

    public String getName() {
        return name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public float getProfitMargin() {
        return profitMargin;
    }
    
    public boolean add(Product e) {
        return products.add(e);
    }

    public boolean remove(Object o) {
        return products.remove(o);
    }

    public boolean addAll(Collection<? extends Product> clctn) {
        return products.addAll(clctn);
    }

    @Override
    public String toString() {
        return "Category{" + "name=" + name + ", products=" + products + ", profitMargin=" + profitMargin + '}';
    }
    
    
}
