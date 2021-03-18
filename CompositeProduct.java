/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.eap.plh24.ask2020_2_3.model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author alefrag
 */
public class CompositeProduct extends Product{
    private Map<SimpleProduct,Integer> components = new HashMap<>();

    public CompositeProduct(int id, String name) {
        super(id, name, new Category("Composite",0.0f));
    }
    
    public CompositeProduct(int id, String name, Category category, Map<SimpleProduct,Integer> components) {
        super(id, name, category);
        this.components = components;
    }
    
    public void addProduct(SimpleProduct k, Integer v) {
        components.put(k, v);
    }

    public void remove(Object o) {
        components.remove(o);
    }

    public void putAll(Map<? extends SimpleProduct, ? extends Integer> map) {
        components.putAll(map);
    }
    
    @Override
    public float getCost() {
        float cost = 0.0f;
        for(Product p : components.keySet())
            cost+=p.getCost()*components.get(p);
        return cost;
    }

    @Override
    public float getPrice(Order o) {
        float price = 0.0f;
        /*
            Κάθε σύνθετο προϊόν έχει αρχική τιμή το άθροισμα των τιμών των προϊόντων που το αποτελούν.
            Αν περιέχει τουλάχιστον 3 ίδια προϊόντα, υπάρχει έκπτωση 5% στην τιμή αυτών των προϊόντων.
            Η τελική τιμή μειώνεται κατά 5% επιπλέον αν η αρχική τιμή είναι πάνω από 100€ και κατά 10% αν είναι πάνω από 300€.
            H τελική τιμή μειώνεται κατά 1% επιπλέον για παραγγελίες με παραλαβή από την αποθήκη.
            Η τελική τιμή στρογγυλοποιείται στο πλησιεστερο αριθμό που το ακέραιο μέρος του τελειώνει σε 9
        */
            
        /*****************TODO******************/
        //Γραψτε την υλοποίηση την μεθόδου

        float totalInitialPrice = 0.0f;

        for(Map.Entry<SimpleProduct, Integer> entry : components.entrySet()){
            float componentPrice = entry.getKey().getPrice(o) * entry.getValue();
            totalInitialPrice += componentPrice;
            if(entry.getValue() >= 3){
                componentPrice *= 0.95f;
            }
            price += componentPrice;
        }

        if(totalInitialPrice > 300){
            price *= 0.90f;
        }else if(totalInitialPrice > 100){
            price *= 0.95f;
        }

        //παραλαβή από την αποθήκη
        if(o.getDispatchType().getName() == "Warehouse"){
            price *= 0.99f;
        }

        //αποκοπή των δεκαδικών ψηφίων μετά τα δυο πρώτα
        price = (float) ((int) (price * 100f)) /100f;
        //στρογγυλοποίηση του δεκαδικού μέρους
        price = Math.round(price);
        //ο κοντινότερος ακέραιος που τελειώνει σε "9" από τα αριστερά
        float near = (int) (price / 10) * 10 - 1;
        //ο κοντινότερος ακέραιος που τελειώνει σε "9" από τα δεξιά
        float far = (int) (price / 10) * 10 + 9;

        price = (price - near > far - price) ? far : near;

        /*****************TODO******************/
        return price;
    }

    @Override
    public float getWeight() {
        float weight = 0.0f;
        for(Product p : components.keySet())
            weight+=p.getWeight()*components.get(p);
        return weight;
    }

    public Map<SimpleProduct,Integer> getComponents() {
        return components;
    }

    @Override
    public String toString() {
        return "CompositeProduct{" + super.toString() + " components=" + components + '}';
    }  
    
}
