package gr.eap.plh24.ask2020_2_3.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DispatchType2 extends DeliveryMethod{
    public DispatchType2(String name, float maxWeight) {
        super(name, maxWeight);
        this.setName("Courier");
        this.setMaxWeight(5);
    }

    @Override
    public float getCost(Order o) {
        if(o.getTotal() <= 300){
            return 5;
        }
        return 0;
    }

    @Override
    public boolean isEligible(Order o) {
        if(o.getWeight() <= getMaxWeight()){
            return true;
        }
        return false;
    }

    @Override
    public void calculateDeliveryDate(Order o){
        //ορίζει την ημερομηνία παράδοσης της παραγγελίας
        //με βάση την ώρα που έχει ολοκληρωθεί στην αποθήκη
        LocalDate myDays =LocalDate.from(o.getDispatchDate().plusDays(1));
        LocalTime myTime = LocalTime.of(9, 0, 0);
        o.setDeliveryDate(LocalDateTime.of(myDays, myTime));
    }
}
/*
           2. Αποστολή με εταιρεία ταχυμεταφοράς:
              Κόστος: 5€ για παραγγελίες μέχρι 300€, δωρεάν για παραγγελίες με μεγαλύτερο κόστος
              Χρόνος Παράδοσης: Επόμενη εργάσιμη μετά την ολοκλήρωση της παραγγελλίας 09:00
              Περιορισμός βάρους: Μέχρι 5Κg
*/
