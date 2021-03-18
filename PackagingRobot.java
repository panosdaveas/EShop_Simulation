/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.eap.plh24.ask2020_2_3.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author alefrag
 */
public class PackagingRobot {
    private int id;
    private Map<Category, Integer> setupTime;
    private Map<Product, Integer> processingTime;
    private int resetTime;
    
    private boolean available = true;
    LocalDateTime currentTime = LocalDateTime.of(2000, 1, 1, 0, 0);

    public PackagingRobot(int id, Map<Category, Integer> setupTime, Map<Product, Integer> processTime, int resetTime) {
        this.id = id;
        this.setupTime = setupTime;
        this.processingTime = processTime;
        this.resetTime = resetTime;
    }

    public boolean isAvailable() {
        return available;
    }   

    public LocalDateTime getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(LocalDateTime currentTime) {
        this.currentTime = currentTime;
    }   

    public void updateTime(Product k, Integer v) {
        processingTime.put(k, v);
    }

    public void updateTime(Category k, Integer v) {
        setupTime.put(k, v);
    }

    public int getPackagerId() { return this.id;}

    //extra Methods//
    public int getTime(SimpleProduct p, int qnt, List<Category> tempCategories){
        int time = 0;
        if(!tempCategories.contains(p.getCategory())) {
            tempCategories.add(p.getCategory());
            time += setupTime.get(p.getCategory());
        }
        time += processingTime.get(p) * qnt;
        return  time;
    }

    public int getTime(CompositeProduct cp, int qnt, List<Category> tempCategories){
        int time = 0;
        for(Map.Entry<SimpleProduct, Integer> p : cp.getComponents().entrySet()){
            time += getTime((SimpleProduct) p.getKey(), p.getValue(), tempCategories) * qnt;
        }
        return time;
    }
    ////////////

    public int processOrder(Order o, LocalDateTime processTime){
        int totalProcessTime=1;
        /*
            Η μηχανή είναι έξυπνη, ταξινομεί τα προϊοντα της κάθε παραγγελίας ανα κατηγορία
            και μετά επεξεργάζεται τα προϊόντα της παραγγελίας της κάθε κατηγορίας.
            Στο τέλος της επεξεργασίαςς κάθε παραγγελίας επανέρχεται στην αρχική κατάσταση 
            καταναλλώνοντας το χρόνο επαναφοράς. Όλοι οι χρόνοι είναι σε λεπτά.
        */
        
        //************************** TODO *******************************
        
        // Kάντε χρήση στοιχείων την παραγγελίας (Προϊόντα, χρόνοι, κατάσταση)
        // Ενημερώστε την κατάσταση και το χρόνο επεξεργασίας με την τρέχουσα ώρα

        //Για κάθε παραγγελία, αποθηκεύουμε τις κατηγορίες των προιόντων
        //σε μια προσωρινή λίστα ώστε να υπολογίζουμε το setupTime μια
        //φορά για όσα προιόντα ανήκουν στην ίδια κατηγορία (μεθόδος getTime())
        //Το χρονικό διάστημα που θα είναι απασχολημένος ο packager(currentTime) είναι το
        //άθροισμα της ώρας που έρχεται από την παραγγελία (processTime) με τον χρόνο
        //επεξεργασίας της παραγγελίας
        o.setState(Order.OrderState.PROCESSED);
        List<Category> tempCategories = new ArrayList<Category>();
        for(Map.Entry<Product, Integer> p : o.getProducts().entrySet()){
            if(p.getKey() instanceof CompositeProduct){
                totalProcessTime += getTime((CompositeProduct) p.getKey(), p.getValue(), tempCategories);
            }else {
                totalProcessTime += getTime((SimpleProduct) p.getKey(), p.getValue(), tempCategories);
            }
        }
        totalProcessTime += resetTime;
        setCurrentTime(processTime.plusMinutes(totalProcessTime));
        //System.out.println(o.getId() + "  " + totalProcessTime + "\n");
        //************************** TODO *******************************
        return totalProcessTime;
    }

    public void setAvailable(boolean b) {
        available = b;
    }
}
