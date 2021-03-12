package db.entity.delivery;

public class DeliverySelect {


    private int deliveryId;
    private String receiverName;
    private String receiverSurname;
    private String address;
    private String sendDate;
    private String deliveryDate;
    private int price;
    private String cityFrom;
    private String cityTo;
    private String status;

    public DeliverySelect(int deliveryId, String address, String receiverName, String receiverSurname,
                          String sendDate, String deliveryDate, int price, String cityFrom, String cityTo, String status){
        this.deliveryId = deliveryId;
        this.receiverName = receiverName;
        this.receiverSurname = receiverSurname;
        this.address = address;
        this.sendDate = sendDate;
        this.deliveryDate = deliveryDate;
        this.price = price;
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.status = status;
    }

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }

    public int getDeliveryId() {
        return deliveryId;
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

    public void setCityFrom(String cityFrom) {
        this.cityFrom = cityFrom;
    }

    public String getCityFrom() {
        return cityFrom;
    }

    public String getCityTo() {
        return cityTo;
    }

    public void setCityTo(String cityTo) {
        this.cityTo = cityTo;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
