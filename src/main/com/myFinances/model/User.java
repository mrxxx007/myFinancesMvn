package main.com.myFinances.model;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Sergey Popov on 4/28/2014.
 */
public class User {
    private int id = -1;
    @NotNull
    private String login;
    private String password;
    private double balance;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        balance = 0;
    }

    public User() {
    }

    //region Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void addToBalance(double amount) {
        balance += amount;
    }
}
