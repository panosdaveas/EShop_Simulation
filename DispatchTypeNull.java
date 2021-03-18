package gr.eap.plh24.ask2020_2_3.model;

public class DispatchTypeNull extends DeliveryMethod{
    public DispatchTypeNull(String name, float maxWeight) {
        super(name, maxWeight);
        this.setName(null);
        this.setMaxWeight(0);
    }

    @Override
    public float getCost(Order o) {
        return 0;
    }

    @Override
    public boolean isEligible(Order o) {
        return false;
    }

    @Override
    public void calculateDeliveryDate(Order o) {
        o.setDeliveryDate(null);
    }
}
/*
    μια κλαση που αρχικοποιεί το DispatchType.
    χρειάστηκε για να μπορούμε να αναθέσουμε τιμές σε
    πεδία όπως το DispatchName στις παραγγελίες που τελικά
    δεν θα εκπληρωθούν
 */
