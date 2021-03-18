package gr.eap.plh24.ask2020_2_3.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;

public class DispatchType1 extends DeliveryMethod{

    public DispatchType1(String name, float maxWeight) {
        super(name, maxWeight);
        this.setName("Warehouse");
        this.setMaxWeight(Float.MAX_VALUE);
    }

    @Override
    public float getCost(Order o) {
        return 0;
    }

    @Override
    public boolean isEligible(Order o) {
        return true;
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
           1. Παραλαβή από την αποθήκη:
              Κόστος: 0€
              Χρόνος Παράδοσης: Επόμενη εργάσιμη μετά την ολοκλήρωση της παραγγελλίας 09:00
*/
