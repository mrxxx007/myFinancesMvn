package main.com.myFinances.model;

/**
 * Created by Sergey Popov on 4/30/2014.
 */
public class Category {
    private int id = -1;
    private String name;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
}
