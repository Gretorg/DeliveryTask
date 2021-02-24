package db.entity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RoutesTest {

    Routes routes = null;

    @Before
    public void setUp() {
        routes = new Routes(1,"Киев","Харьков",200);
    }

    @Test
    public void getIdTest() {
        Assert.assertEquals(1, routes.getId());
    }

    @Test
    public void setIdTest() {
        routes.setId(2);
        Assert.assertEquals(2, routes.getId());
    }

    @Test
    public void getCityFromTest() {
        Assert.assertEquals("Киев", routes.getCityFrom());
    }

    @Test
    public void setCityFromTest() {
        routes.setCityFrom("Одесса");
        Assert.assertEquals("Одесса", routes.getCityFrom());
    }

    @Test
    public void getCityToTest() {
        Assert.assertEquals("Харьков", routes.getCityTo());
    }

    @Test
    public void setCityToTest() {
        routes.setCityTo("Одесса");
        Assert.assertEquals("Одесса", routes.getCityTo());
    }

    @Test
    public void getDistanceTest() {
        Assert.assertEquals(200, routes.getDistance());
    }

    @Test
    public void setDistanceTest() {
        routes.setDistance(256);
        Assert.assertEquals(256, routes.getDistance());
    }

    @Test
    public void equalsSameTest() {
        Assert.assertEquals(routes, routes);
    }

    @Test
    public void equalsTest() {
        Routes expected = new Routes(1,"Киев","Харьков",200);
        Assert.assertEquals(expected.toString(), routes.toString());
    }
}
