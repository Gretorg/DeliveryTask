package db.entity.delivery;

import db.entity.users.Users;

public class DeliveryBuilder implements BuilderDelivery{

    private DeliverySelect deliverySelect;
    private DeliveryInsert deliveryInsert;

    @Override
    public void setDeliveryInsert(DeliveryInsert deliveryInsert) {
        this.deliveryInsert = deliveryInsert;
    }

    @Override
    public void setDeliverySelect(DeliverySelect deliverySelect) {
        this.deliverySelect = deliverySelect;
    }

    public Delivery getResult(){
        return new Delivery(deliverySelect,deliveryInsert );
    }

}
