package db.entity.delivery;

public class DeliveryInsert {


    private int cargoId;
    private int routeId;
    private int userId;
    private String receiverName;
    private String receiverSurname;
    private String address;
    private String sendDate;
    private String deliveryDate;
    private int price;

    public DeliveryInsert(int routeId, int cargoId, int userId, String receiverName,
                          String receiverSurname, String address, String sendDate, String deliveryDate, int price){
        this.routeId = routeId;
        this.cargoId = cargoId;
        this.userId = userId;
        this.receiverName = receiverName;
        this.receiverSurname = receiverSurname;
        this.address = address;
        this.sendDate = sendDate;
        this.deliveryDate = deliveryDate;
        this.price = price;
    }

    public int getCargoId() {
        return cargoId;
    }

    public void setCargoId(int cargoId) {
        this.cargoId = cargoId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverSurname(String receiverSurname) {
        this.receiverSurname = receiverSurname;
    }

    public String getReceiverSurname() {
        return receiverSurname;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

}
