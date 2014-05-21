package main.com.myFinances.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import main.com.myFinances.model.Importance;

/**
 * Created by Sergey Popov on 4/28/2014.
 */
public class Notes {
    private int id = -1;
    private double amount;
    private Date date;
    private String info;
    //private String category;
    //private int category_id;
    private Category category;
    private Importance importance;

    public Notes() {
        this.amount = 0;
        this.date = null;
        this.category = new Category();
        this.category.setId(1);
        this.info = "note_info";
    }

    public Notes(double amount, Date date) {
        this.amount = amount;
        this.date = date;
    }



    //region Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Importance getImportance() {
        return importance;
    }

    public void setImportance(Importance importance) {
        this.importance = importance;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    /*public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }*/

    //endregion
}
