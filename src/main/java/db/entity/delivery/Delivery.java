package db.entity.delivery;

/**
 * Delivery entity
 *
 * @author  Vlad Salimovskyi
 */
public class Delivery {

    private final DeliveryInsert deliveryInsert;
    private final DeliverySelect deliverySelect;

    public Delivery(DeliverySelect deliverySelect, DeliveryInsert deliveryInsert){
        this.deliverySelect = deliverySelect;
        this.deliveryInsert = deliveryInsert;
    }

    public DeliveryInsert getDeliveryInsert() {
        return deliveryInsert;
    }

    public DeliverySelect getDeliverySelect() {
        return deliverySelect;
    }
}
