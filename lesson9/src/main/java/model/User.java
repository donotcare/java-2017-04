package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class User extends DataSet {
    @Column(name = "name")
    private String personName;
    private int age;

    public User() {}

    public User(int age, String personName) {
        this.age = age;
        this.personName = personName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

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
