/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.eap.plh24.ask2020_2_3.model;

import java.util.Map;

/**
 *
 * @author alefrag
 */
public class SimpleProduct extends Product{
    private float cost; 
    private float weight;

    public SimpleProduct(int id, String name, Category category, float cost, float weight) {
        super(id, name, category);
        this.cost = cost;
        this.weight = weight;
    }
        
    @Override
    public float getCost() {
        return cost;
    }
    
    @Override
    public float getWeight() {
        return weight;
    }
        
    @Override
    public float getPrice(Order o) {
        float price = 0.0f;
        /*
            Κάθε προϊόν ανάλογα με την κατηγορία που ανήκει έχει προτεινόμενη τιμή 
            η οποία είναι το αρχικό του κόστος προσαυξημένο με το συντελεστή κέρδους 
            της κατηγορίας που ανήκει
        */
        
        /**********************TODO************/
        // Κάντε τον υπολογισμό με χρήση πληροφοριών από την κατηγορία
        price = getCost() * (1 + getCategory().getProfitMargin());

        /**********************TODO************/

        return price;
    }

    @Override
    public String toString() {
        return "SimpleProduct{"+ super.toString() + " cost=" + cost + ", weight=" + weight + '}';
    }

}
