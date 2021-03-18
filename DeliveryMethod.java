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

/************************TODO**********************/
/* Φτιάξτε τους 3 τρόπους παράδοσης ως υποκλάσεις της DispatchType
           1. Παραλαβή από την αποθήκη:
              Κόστος: 0€
              Χρόνος Παράδοσης: Επόμενη εργάσιμη μετά την ολοκλήρωση της παραγγελλίας 09:00
        
           2. Αποστολή με εταιρεία ταχυμεταφοράς:
              Κόστος: 5€ για παραγγελίες μέχρι 300€, δωρεάν για παραγγελίες με μεγαλύτερο κόστος
              Χρόνος Παράδοσης: Επόμενη εργάσιμη μετά την ολοκλήρωση της παραγγελλίας 09:00
              Περιορισμός βάρους: Μέχρι 5Κg
        
           2. Μεταφορική εταιρεία:
              Κόστος: 10€ για παραγγελίες μέχρι 35Κg, 15€ για βαρύτερες παραγγελίες
              Χρόνος Παράδοσης: Δύο ημέρες μετά την ολοκλήρωση της παραγγελλίας 12:00
              Περιορισμός βάρους: Χωρίς περιορισμό βάρους
*/

import java.time.LocalDateTime;

/************************TODO**********************/

public abstract class DeliveryMethod {
    private String name;
    //private int processingTime;
    private float maxWeight;

    public DeliveryMethod(String name, float maxWeight) {
        this.name = name;
        //this.processingTime = processingTime;
        this.maxWeight = maxWeight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //public int getProcessingTime() {
    //    return processingTime;
    //}

    //public void setProcessingTime(int processingTime) {
    //    this.processingTime = processingTime;
    //}

    public float getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(float maxWeight) {
        this.maxWeight = maxWeight;
    }
    
    public abstract float getCost(Order o);
    public abstract boolean isEligible(Order o);

    //extra Method//
    public abstract void calculateDeliveryDate(Order o);
    ///////////////
}
