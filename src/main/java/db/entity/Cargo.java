package db.entity;

/**
 * Cargo entity
 *
 * @author  Vlad Salimovskyi
 */
public class Cargo {


    private int id;
    private int weight;
    private int length;
    private int width;
    private int height;
    private String description;

    public Cargo(int weight, int length ,int width, int height, String description){

        this.weight = weight;
        this.length = length;
        this.width = width;
        this.height = height;
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Cargo{" +
                "id=" + id +
                ", weight=" + weight +
                ", length=" + length +
                ", width=" + width +
                ", height=" + height +
                ", description='" + description + '\'' +
                '}';
    }
}
