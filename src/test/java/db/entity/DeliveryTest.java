package db.entity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DeliveryTest {

    Delivery delivery = null;

    @Before
    public void setUp() {
        delivery = new Delivery(1,"Freedom street","Vlad","Ivanov",
                "2021-02-24","2021-02-26", 200,"Харьков","Киев","Оплачено");
    }

    @Test
    public void getIdTest() {
        Assert.assertEquals(1, delivery.getDeliveryId());
    }

    @Test
    public void setIdTest() {
        delivery.setDeliveryId(2);
        Assert.assertEquals(2, delivery.getDeliveryId());
    }

    @Test
    public void getAddressTest() {
        Assert.assertEquals("Freedom street", delivery.getAddress());
    }

    @Test
    public void setAddressTest() {
        delivery.setAddress("Freedom street 2");
        Assert.assertEquals("Freedom street 2", delivery.getAddress());
    }

    @Test
    public void getReceiverNameTest() {
        Assert.assertEquals("Vlad", delivery.getReceiverName());
    }

    @Test
    public void setReceiverNameTest() {
        delivery.setReceiverName("Ivan");
        Assert.assertEquals("Ivan", delivery.getReceiverName());
    }

    @Test
    public void getReceiverSurnameTest() {
        Assert.assertEquals("Ivanov", delivery.getReceiverSurname());
    }

    @Test
    public void setReceiverSurnameTest() {
        delivery.setReceiverSurname("Petrov");
        Assert.assertEquals("Petrov", delivery.getReceiverSurname());
    }

    @Test
    public void getSendDateTest() {
        Assert.assertEquals("2021-02-24", delivery.getSendDate());
    }

    @Test
    public void setSendDateTest() {
        delivery.setSendDate("2021-02-25");
        Assert.assertEquals("2021-02-25", delivery.getSendDate());
    }

    @Test
    public void getDeliveryDateTest() {
        Assert.assertEquals("2021-02-26", delivery.getDeliveryDate());
    }

    @Test
    public void setDeliveryDateTest() {
        delivery.setDeliveryDate("2021-02-25");
        Assert.assertEquals("2021-02-25", delivery.getDeliveryDate());
    }

    @Test
    public void getPriceTest() {
        Assert.assertEquals(200, delivery.getPrice());
    }

    @Test
    public void setPriceTest() {
        delivery.setPrice(100);
        Assert.assertEquals(100, delivery.getPrice());
    }

    @Test
    public void getCityFromTest() {
        Assert.assertEquals("Харьков", delivery.getCityFrom());
    }

    @Test
    public void setCityFromTest() {
        delivery.setCityFrom("Одесса");
        Assert.assertEquals("Одесса", delivery.getCityFrom());
    }

    @Test
    public void getCityToTest() {
        Assert.assertEquals("Киев", delivery.getCityTo());
    }

    @Test
    public void setCityToTest() {
        delivery.setCityTo("Одесса");
        Assert.assertEquals("Одесса", delivery.getCityTo());
    }

    @Test
    public void getStatusTest() {
        Assert.assertEquals("Оплачено", delivery.getStatus());
    }

    @Test
    public void setStatusTest() {
        delivery.setStatus("Ожидаеться оплата");
        Assert.assertEquals("Ожидаеться оплата", delivery.getStatus());
    }

    @Test
    public void equalsSameTest() {
        Assert.assertEquals(delivery, delivery);
    }

    @Test
    public void equalsTest() {
        Delivery expected = new Delivery(1,"Freedom street","Vlad","Ivanov",
                "2021-02-24","2021-02-26", 200,"Харьков","Киев","Оплачено");
        Assert.assertEquals(expected.toString(), delivery.toString());
    }

}
