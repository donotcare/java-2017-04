package ru.otus.l16.backend.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "addresses")
public class AddressDataSet extends DataSet{

    private String street;
    private int index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
