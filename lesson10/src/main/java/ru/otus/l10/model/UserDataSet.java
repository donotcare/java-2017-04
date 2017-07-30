package ru.otus.l10.model;


import com.google.common.collect.ImmutableList;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Users")
public class UserDataSet extends DataSet {

    @Column(name = "name")
    private String personName;
    private int age;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id", nullable = false)
    private List<PhoneDataSet> phones;

    public UserDataSet() {
    }

    public UserDataSet(int age, String personName) {
        this.age = age;
        this.personName = personName;
    }

    public List<PhoneDataSet> getPhones() {
        return ImmutableList.copyOf(phones);
    }

    public void setPhones(List<PhoneDataSet> phones) {
        this.phones = phones;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPersonName() {
        return personName;
    }

    public int getAge() {
        return age;
    }

    public void addPhone(PhoneDataSet phone) {
        if (phones == null)
            phones = new ArrayList<>();
        phones.add(phone);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        UserDataSet user = (UserDataSet) o;

        return age == user.age && (personName != null ? personName.equals(user.personName) : user.personName == null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (personName != null ? personName.hashCode() : 0);
        result = 31 * result + age;
        return result;
    }

}
