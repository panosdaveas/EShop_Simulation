/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.eap.plh24.ask2020_2_3.model;

/**
 *
 * @author alefrag
 */
public abstract class Product {
    private int id;
    private String name;
    private Category category;

    public Product(int id, String name, Category category) {
        this.id = id;
        this.name = name;
        this.category = category;
        category.add(this);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return getName();
    }
    
    public abstract float getCost();
    public abstract float getPrice(Order o);
    public abstract float getWeight();
}
