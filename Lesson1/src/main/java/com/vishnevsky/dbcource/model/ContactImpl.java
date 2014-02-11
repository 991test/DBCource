package com.vishnevsky.dbcource.model;

public class ContactImpl implements Contact {

    private String name;
    private String phoneNumber;
    private String info;

    public ContactImpl(String name, String phoneNumber, String info) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.info = info;
    }

    public ContactImpl() {
        this("", "", "");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String getInfo() {
        return info;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Contact: " + name +
                ". Phone number: " + phoneNumber +
                ", info: " + info;
    }
}
