/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.eap.plh24.ask2020_2_3.model;

import java.util.Hashtable;
import java.util.Map;

/**
 *
 * @author alefrag
 */
public class Inventory {
    private final Map<SimpleProduct, Integer> availableProducts = new Hashtable<>();

    public Iterable<Map.Entry<SimpleProduct,Integer>> getAvailableProducts() {
        return availableProducts.entrySet();
    }
        
    public void addProducts(Map<SimpleProduct, Integer> newProducts){
         if(newProducts==null) return;
         for (Map.Entry<SimpleProduct,Integer> entry : newProducts.entrySet()){
             if(availableProducts.containsKey(entry.getKey()))
                 availableProducts.replace(entry.getKey(), 
                         availableProducts.get(entry.getKey()) + entry.getValue());
             else
                 availableProducts.put(entry.getKey(),entry.getValue());
         }
    }

    public boolean hasProduct(SimpleProduct p) {
        return availableProducts.containsKey(p);
    }
    
    public boolean canGetProducts(SimpleProduct p, int qnt) {
        if(availableProducts.containsKey(p))
            return availableProducts.get(p)>=qnt;
        else
            return false;
    }
    
    public boolean canGetProducts(CompositeProduct cp, int qnt) {
        boolean ret=true;
        for (Map.Entry<SimpleProduct, Integer> p: cp.getComponents().entrySet())
        {
            ret &= canGetProducts(p.getKey(),p.getValue()*qnt);
        }
        return ret;
    }
    
    public boolean removeProducts(SimpleProduct p, int qnt) {
        if(canGetProducts(p,qnt)){
            int newStock = availableProducts.get(p) - qnt;
            if (newStock>0)
                availableProducts.replace((SimpleProduct)p,newStock);
            else
                availableProducts.remove(p);
            return true;
        }
        else
            return false;
    }
    
    public boolean removeProducts(CompositeProduct cp, int qnt) {
        boolean ret=true;
        for (Map.Entry<SimpleProduct, Integer> p: cp.getComponents().entrySet())
        {
            ret &= removeProducts(p.getKey(),p.getValue()*qnt);
        }
        return ret;
    }


}
