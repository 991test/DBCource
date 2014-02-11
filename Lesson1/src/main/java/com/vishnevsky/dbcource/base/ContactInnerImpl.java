package com.vishnevsky.dbcource.base;

import com.vishnevsky.dbcource.model.Contact;

class ContactInnerImpl implements Contact {

    private String name;
    private String phoneNumber;
    private String info;

    public ContactInnerImpl(Contact contact) {
        name = contact.getName();
        phoneNumber = contact.getPhoneNumber();
        info = contact.getInfo();
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

    @Override
    public String toString() {
        return "Contact: " + name +
                ". Phone number: " + phoneNumber +
                ", info: " + info;
    }
}
