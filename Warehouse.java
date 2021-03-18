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
public class Warehouse {
    private ArrayList<PackagingRobot> packagers;
    private SortedSet<Order> pendingOrders = new TreeSet<>();
    //extra variable//
    private SortedSet<Order> cancelledOrders = new TreeSet<>();
    /////////////////
    Inventory inventory = new Inventory(); //Τι θα συμβεί αν έχουμε έχουμε περισσότερους αποθηκευτικούς χώρους;;
    LocalDateTime currentTime = LocalDateTime.of(2000, 1, 1, 0, 0);

    public Warehouse(ArrayList<PackagingRobot> packagers) {
        this.packagers = packagers;
    }
    
    public void addOrder(Order o) {
        if(currentTime.isBefore(o.getOrderTime()))
            currentTime = o.getOrderTime();
        if(o.getState()==Order.OrderState.SUBMITTED){
            pendingOrders.add(o);
            processOrders();
        }
    }
    /////////////////
    //extra Methods//
    public boolean canGetOrder(Order o){
        //διατρέχει όλα τα προιόντα της
        //παραγγελίας και αποφασίζει εάν
        //υπάρχουν στο inventory
        boolean flag = false;
        for(Map.Entry<Product, Integer> entry : o.getProducts().entrySet()){
            if(checkInventory(entry.getKey(), entry.getValue())){
                flag = true;
            }
            else {
                flag = false;
            }
        }
        return flag;
    }

    public void removeOrderFromInventory(Order o){
        //αφαιρεί τα προιόντα μιας εκπληρωμένης
        //παραγγελίας από το inventory
        for(Map.Entry<Product, Integer> entry : o.getProducts().entrySet()){
            removeFromInventory(entry.getKey(), entry.getValue());
        }
    }

    public PackagingRobot assignPackager(){
        //αναθέτει στην παραγγελία έναν
        //διαθέσιμο packager. Αλλιώς επιστρέφει
        //την τιμή null.
        for(int i = 0; i < packagers.size(); i++){
            if(packagers.get(i).isAvailable()){
                return packagers.get((i));
            }
        }
        return null;
    }

    public void removeFinishedOrders(){
        //αφαιρεί τις εκπληρωμένες παραγγελίες
        //από την λίστα των pendingOrders
        Iterator<Order> it = pendingOrders.iterator();
        while (it.hasNext()){
            Order o = it.next();
            if(o.getState() != Order.OrderState.SUBMITTED){
                it.remove();
            }
        }
    }

    public void releasePackagers(){
        //ελευθερώνει τους packagers που έχουν
        //τελειώσει το πακετάρισμα.
        //*προσοχή* το χρονικό βήμα(currentTime) που ελέγχεται
        //η διαθεσιμότητα των packagers
        //είναι η στιγμή καταχώρησης μιας νέας παραγγελίας
        //ή όταν συμβαίνει ένα restock
        for (int i= 0; i < packagers.size(); i++){
            if(!packagers.get(i).isAvailable() && currentTime.isAfter(packagers.get(i).getCurrentTime())){
                packagers.get(i).setAvailable(true);
            }
        }
    }

    public void setDeliveryMethod(Order o){
        //αναθέτει στην τύχη έναν τρόπο παράδοσης
        //στην παραγγελία. Εάν ο τρόπος αυτός παράδοσης
        //δεν μπορεί να εφαρμοστεί στην παραγγελία, τότε
        //αλλάζει στον default τρόπο που είναι η
        //παραλαβή από την αποθήκη
        String name;
        Random rand = new Random();
        int dice = 1 + rand.nextInt(3);

        switch (dice) {
            case 1:
                name = "Warehouse";
                break;
            case 2:
                name = "Courier";
                break;
            case 3:
                name = "Cargo";
                break;
            default:
                name = "Warehouse";
            break;
        }
        if(name == "Warehouse"){
            o.setDispatchType(new DispatchType1(name, 0));
            o.getDispatchType().calculateDeliveryDate(o);
        }else if(name == "Courier"){
            o.setDispatchType(new DispatchType2(name, 5));
            if(!o.getDispatchType().isEligible(o)){
                o.setDispatchType(new DispatchType1("Warehouse", 0));
            }
            o.getDispatchType().calculateDeliveryDate(o);
        }else if(name == "Cargo"){
            o.setDispatchType(new DispatchType3(name, 0));
            o.getDispatchType().calculateDeliveryDate(o);
        }

    }

    public void printCancelledOrders(){
        int i=0;
        System.out.println("Ακυρωμένες παραγγελίες " + "    " + cancelledOrders.size());
        //for(Order o : cancelledOrders){
        //    System.out.println((i++)+"\t"+o.getId());
        //}
    }
    /////////////

    private void processOrders(){
        /*
            Επεξεργάζεται τις παραγγελίες για τις οποίες είναι διαθέσιμα όλα τα προϊόντα τους.
            Μπορεί να κάνει χρήση μόνο των διαθέσιμων ρομπότ
            Κάθε ρομποτ αναλαμβάνει μια παραγγελία και δεν είναι διαθέσιμο μέχρι να την ολοκληρώσει
            Οι παλαιότερες παραγγελίες έχουν προτεραιότητα (FIFO)
            Η διαδικασία σταματάει αν δεν μπορούν να εξυπηρετηθούν άλλες παραγγελίες λόγω
            έλλειψης προϊόντων.
        */

        /***************** TODO ********************************/
        //Κάντε χρήση των μεθόδων του inventory και των ρομπότ
        //Προσέξτε να συγχρονίζεται σωστά τους χρόνους 
        //Ακυρώστε παραγγελίες που δεν μπορούν αν ικανοποιηθούν πάνω από 15 ημέρες

        //Η processOrders καλείται όταν έχουμε μια καινούρια παραγγελία οπότε ο χρόνος άφιξης
        //είναι το currentTime μας, όπου και ελέγχουμε εάν μπορεί να ελευθερωθεί
        //κάποιος από τους απασχολημένους packagers με την releasePackagers.
        //Εάν μια παραγγελία δεν μπορεί να συλλεχθεί μετά από 15 μέρες από την ημερομηνία
        //υποβολής της, τότε ακυρώνεται και μπαίνει στην λίστα των cancelledOrders.
        //Εάν υπάρχει διαθέσιμος packager και τα προιόντα που περιλαμβάνει είναι διαθέσιμα στην
        //αποθήκη, τότε ξεκινά η συλλογή της καλόντας τις μεθόδους της packagingRobot.
        //στο τέλος της μεθόδου αφαιρούμε από την λίστα των pendingOrders της ολοκληρωμένες παραγγελίες

        releasePackagers();
        Iterator<Order> it = getPendingOrders().iterator();
        while (it.hasNext()){
            Order o = it.next();
            if(o.getState() == Order.OrderState.SUBMITTED && currentTime.isAfter(o.getOrderTime().plusDays(15))){
                o.setState(Order.OrderState.CANCELLED);
                cancelledOrders.add(o);
            }
            int processTime = 0;
            if(o.getState() == Order.OrderState.SUBMITTED && canGetOrder(o)){
                PackagingRobot myRobot = assignPackager();
                if(myRobot != null){
                    myRobot.setAvailable(false);
                    processTime = myRobot.processOrder(o, currentTime);
                    o.setPackagerId(myRobot.getPackagerId());
                    o.setDispatchDate(currentTime.plusMinutes(processTime));
                    o.setState(Order.OrderState.FINISHED);
                    removeOrderFromInventory(o);
                    setDeliveryMethod(o);
                }
            }
        }
        removeFinishedOrders();
        /***************** TODO ********************************/
    }
    
    private boolean checkInventory(Product p, int qnt){
        if(p instanceof CompositeProduct)
            return inventory.canGetProducts((CompositeProduct)p,qnt);
        else
            return inventory.canGetProducts((SimpleProduct)p,qnt);
    }
    
    private boolean removeFromInventory(Product p, int qnt){
        if(p instanceof CompositeProduct)
            return inventory.removeProducts((CompositeProduct)p,qnt);
        else
            return inventory.removeProducts((SimpleProduct)p,qnt);
    }
    
    void restockProducts(Map<SimpleProduct, Integer> newStock, LocalDateTime restockTime) {
        //sync clock
        if (restockTime!=null && currentTime.isBefore(restockTime))
            currentTime = restockTime.plusMinutes(0);
        inventory.addProducts(newStock);
        //Έχουμε νέα προϊόντα, να δοκιμάσουμε να υλοποιήσουμε παραγγελίες
        processOrders();
    }

    public SortedSet<Order> getPendingOrders() {
        return pendingOrders;
    }   
    
    public void printPendingOrders(){
        int i=0;

        System.out.println("Παραγγελίες σε εκκρεμότητα " + pendingOrders.size());
//        for(Order o : pendingOrders){
//            System.out.println((i++)+"\t"+o.getId());
//        }
    }
    
    public Iterable<Map.Entry<SimpleProduct,Integer>> getStock(){
        return inventory.getAvailableProducts();
    }
}
