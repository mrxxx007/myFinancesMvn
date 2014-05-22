package com.myFinances.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by admin on 03.05.14.
 */
public class Param{
    private int categoryID;
    private Date fromdate, todate;
    private Double minamount;

    public Param(int categoryID, Date fromdate, Date todate, double minamount) {
        this.categoryID = categoryID;
        this.fromdate = fromdate;
        this.todate = todate;
        this.minamount = minamount;
    }

    public Param() {

        this.minamount = new Double(0);
        this.fromdate = null;
        this.todate = null;
        this.categoryID = -1;

    }

    public int getCategoryID() {
        return categoryID;
    }

    public Date getFromdate() {
        return fromdate;
    }

    public Date getTodate() {
        return todate;
    }

    public Double getMinamount() {
        return minamount;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public void setFromdate(Date date) throws ParseException {

       this.fromdate = date;
    }

    public void setTodate(Date date) throws ParseException {

        this.todate = date;
    }

    public void setMinamount(Double minamount) {
        if (minamount == null) {
            this.minamount = new Double(0);
        } else {
            this.minamount = minamount;
        }
    }
}
