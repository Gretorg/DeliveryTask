package db.entity;

import java.util.Objects;

/**
 * Routes entity
 *
 * @author  Vlad Salimovskyi
 */
public class Routes {

    private int id;
    private String cityFrom;
    private String cityTo;
    private int distance;

    public Routes(int id, String cityFrom, String cityTo, int distance){
        this.id = id;
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.distance = distance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setCityFrom(String cityFrom) {
        this.cityFrom = cityFrom;
    }

    public String getCityFrom() {
        return cityFrom;
    }

    public void setCityTo(String cityTo) {
        this.cityTo = cityTo;
    }

    public String getCityTo() {
        return cityTo;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Routes routes = (Routes) o;
        return id == routes.id &&
                distance == routes.distance &&
                Objects.equals(cityFrom, routes.cityFrom) &&
                Objects.equals(cityTo, routes.cityTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cityFrom, cityTo, distance);
    }

    @Override
    public String toString() {
        return "Routes{" +
                "id=" + id +
                ", cityFrom='" + cityFrom + '\'' +
                ", cityTo='" + cityTo + '\'' +
                ", distance=" + distance +
                '}';
    }
}
