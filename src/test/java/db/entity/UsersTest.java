package db.entity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class UsersTest {

    Users users = null;

    @Before
    public void setUp() {
        users = new Users("email@gmail.com","123","asdsad",
                "Ivan","Ivanov","2000-10-10",2);
    }

    @Test
    public void getEmailTest() {
        Assert.assertEquals("email@gmail.com", users.getEmail());
    }

    @Test
    public void setEmailTest() {
        users.setEmail("email2@gmail.com");
        Assert.assertEquals("email2@gmail.com", users.getEmail());
    }

    @Test
    public void getPasswordTest() {
        Assert.assertEquals("123", users.getPassword());
    }

    @Test
    public void setPasswordTest() {
        users.setPassword("1234");
        Assert.assertEquals("1234", users.getPassword());
    }

    @Test
    public void getSaltTest() {
        Assert.assertEquals("asdsad", users.getSalt());
    }

    @Test
    public void setSaltTest() {
        users.setSalt("asdsada");
        Assert.assertEquals("asdsada", users.getSalt());
    }

    @Test
    public void getNameTest() {
        Assert.assertEquals("Ivan", users.getFirstName());
    }

    @Test
    public void setNameTest() {
        users.setFirstName("Petr");
        Assert.assertEquals("Petr", users.getFirstName());
    }

    @Test
    public void getSurnameTest() {
        Assert.assertEquals("Ivanov", users.getLastName());
    }

    @Test
    public void setSurnameTest() {
        users.setLastName("Petrov");
        Assert.assertEquals("Petrov", users.getLastName());
    }

    @Test
    public void getBirthDateTest() {
        Assert.assertEquals("2000-10-10", users.getBirthDate());
    }

    @Test
    public void setBirthDateTest() {
        users.setBirthDate("2000-10-11");
        Assert.assertEquals("2000-10-11", users.getBirthDate());
    }

    @Test
    public void getRoleIdTest() {
        Assert.assertEquals(2, users.getRoleId());
    }

    @Test
    public void setRoleIdTest() {
        users.setRoleId(3);
        Assert.assertEquals(3, users.getRoleId());
    }

    @Test
    public void equalsSameTest() {
        Assert.assertEquals(users , users);
    }

    @Test
    public void equalsTest() {
        Users expected =  users = new Users("email@gmail.com","123","asdsad",
                "Ivan","Ivanov","2000-10-10",2);
        Assert.assertEquals(expected.toString(), users .toString());
    }
}
