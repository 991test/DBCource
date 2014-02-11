package com.vishnevsky.dbcource.base;

import com.vishnevsky.dbcource.exceptions.DBException;
import com.vishnevsky.dbcource.model.Contact;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DataBase {

    private ReentrantReadWriteLock reentrantLock = new ReentrantReadWriteLock ();
    private Lock readLock = reentrantLock.readLock();
    private Lock writeLock = reentrantLock.writeLock();

    private Map<String, ContactInnerImpl> contactMap = new HashMap<String, ContactInnerImpl>();

    public Contact create(Contact contact) throws DBException {
        writeLock.lock();
        try {
            if(contactMap.containsKey(contact.getName())) {
                throw new DBException("DB already contains this key.");
            } else {
                ContactInnerImpl contactInner = new ContactInnerImpl(contact);
                contactMap.put(contact.getName(), contactInner);
                return contact;
            }
        } finally {
            writeLock.unlock();
        }
    }

    public Contact update(Contact contact) throws DBException {
        writeLock.lock();
        try {
            if(! contactMap.containsKey(contact.getName())) {
                throw new DBException("DB does not contain this key.");
            } else {
                ContactInnerImpl contactInner = new ContactInnerImpl(contact);
                contactMap.put(contact.getName(), contactInner);

                return contact;
            }
        } finally {
            writeLock.unlock();
        }
    }

    public Contact update(String oldName, Contact contact) throws DBException {
        writeLock.lock();
        try {
            if(! contactMap.containsKey(oldName) ) {
                throw new DBException("DB does not contain this key.");
            } else if( contactMap.containsKey( contact.getName() ) ) {
                throw new DBException("DB already contains this key.");
            } else {
                ContactInnerImpl contactInner = new ContactInnerImpl(contact);
                contactMap.put(contact.getName(), contactInner);
                contactMap.remove(oldName);

                return contact;
            }
        } finally {
            writeLock.unlock();
        }
    }

    public void delete(Contact contact) throws DBException {
        writeLock.lock();
        try {
            if(! contactMap.containsKey(contact.getName())) {
                throw new DBException("DB does not contain this key.");
            } else {
                contactMap.remove(contact.getName());
            }
        } finally {
            writeLock.unlock();
        }
    }

    public Contact get(String name) throws DBException {
        readLock.lock();
        try {
            if(! contactMap.containsKey(name)) {
                throw new DBException("DB does not contain this key.");
            } else {
                return contactMap.get(name);
            }
        } finally {
            readLock.unlock();
        }
    }

}
