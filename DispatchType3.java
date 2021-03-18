package gr.eap.plh24.ask2020_2_3.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DispatchType3 extends DeliveryMethod{
    public DispatchType3(String name, float maxWeight) {
        super(name, maxWeight);
        this.setName("Cargo");
        this.setMaxWeight(Float.MAX_VALUE);
    }

    @Override
    public float getCost(Order o) {
        if(o.getWeight() <= 35){
            return 10;
        }
        return 15;
    }

    @Override
    public boolean isEligible(Order o) {
        return true;
    }

    @Override
    public void calculateDeliveryDate(Order o){
        //ορίζει την ημερομηνία παράδοσης της παραγγελίας
        //με βάση την ώρα που έχει ολοκληρωθεί στην αποθήκη
        LocalDate myDays =LocalDate.from(o.getDispatchDate().plusDays(2));
        LocalTime myTime = LocalTime.of(12, 0, 0);
        o.setDeliveryDate(LocalDateTime.of(myDays, myTime));
    }
}
/*
           3. Μεταφορική εταιρεία:
              Κόστος: 10€ για παραγγελίες μέχρι 35Κg, 15€ για βαρύτερες παραγγελίες
              Χρόνος Παράδοσης: Δύο ημέρες μετά την ολοκλήρωση της παραγγελλίας 12:00
              Περιορισμός βάρους: Χωρίς περιορισμό βάρους
*/
