package db.entity;

/**
 * Delivery entity
 *
 * @author  Vlad Salimovskyi
 */
public class Delivery {

    private int deliveryId;
    private int cargoId;
    private int routeId;
    private int userId;
    private String receiverName;
    private String receiverSurname;
    private String address;
    private String sendDate;
    private String deliveryDate;
    private int price;
    private int statusId;
    private String cityFrom;
    private String cityTo;
    private String status;


    public Delivery(int deliveryId, String address, String receiverName, String receiverSurname,
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



    public Delivery(int routeId, int cargoId, int userId, String receiverName,
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

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }

    public int getDeliveryId() {
        return deliveryId;
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

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getStatusId() {
        return statusId;
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

    @Override
    public String toString() {
        return "Delivery{" +
                "deliveryId=" + deliveryId +
                ", cargoId=" + cargoId +
                ", routeId=" + routeId +
                ", userId=" + userId +
                ", receiverName='" + receiverName + '\'' +
                ", receiverSurname='" + receiverSurname + '\'' +
                ", address='" + address + '\'' +
                ", sendDate='" + sendDate + '\'' +
                ", deliveryDate='" + deliveryDate + '\'' +
                ", price=" + price +
                ", statusId=" + statusId +
                ", cityFrom='" + cityFrom + '\'' +
                ", cityTo='" + cityTo + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
