package models.entities;

import models.enums.CryptoType;

public class Wallet {

    private String id ;
    private String address ;
    private double balance ;
    private String owner ;
    private CryptoType type ;

    public Wallet(String id, String address, double balance , String owner, CryptoType type) {
        this.id = id;
        this.address = address;
        this.balance = balance;
        this.owner = owner;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public double getBalance() {
        return balance;
    }

    public String getOwner() {
        return owner;
    }

    public CryptoType getType() {
        return type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setType(CryptoType type) {
        this.type = type;
    }
}
