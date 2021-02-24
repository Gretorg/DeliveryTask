package db.entity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CargoTest {

    Cargo cargo = null;

    @Before
    public void setUp() {
        cargo = new Cargo(2,20,20,20,"Сейф");
    }

    @Test
    public void getWeightTest() {
        Assert.assertEquals(2, cargo.getWeight());
    }

    @Test
    public void setWeightTest() {
        cargo.setWeight(3);
        Assert.assertEquals(3, cargo.getWeight());
    }

    @Test
    public void getLengthTest() {
        Assert.assertEquals(20, cargo.getLength());
    }

    @Test
    public void setLengthTest() {
        cargo.setLength(30);
        Assert.assertEquals(30, cargo.getLength());
    }

    @Test
    public void getWidthTest() {
        Assert.assertEquals(20, cargo.getWidth());
    }

    @Test
    public void setWidthTest() {
        cargo.setWidth(30);
        Assert.assertEquals(30, cargo.getWidth());
    }

    @Test
    public void getHeightTest() {
        Assert.assertEquals(20, cargo.getHeight());
    }

    @Test
    public void setHeightTest() {
        cargo.setHeight(30);
        Assert.assertEquals(30, cargo.getHeight());
    }

    @Test
    public void getDescriptionTest() {
        Assert.assertEquals("Сейф", cargo.getDescription());
    }

    @Test
    public void setDescriptionTest() {
        cargo.setDescription("Не сейф");
        Assert.assertEquals("Не сейф", cargo.getDescription());
    }

    @Test
    public void equalsSameTest() {
        Assert.assertEquals(cargo, cargo);
    }

    @Test
    public void equalsTest() {
        Cargo expected = new Cargo(2,20,20,20,"Сейф");
        Assert.assertEquals(expected.toString(), cargo.toString());
    }
}
