package model;

import javax.persistence.*;

@Entity
@Table(name = "Phones")
public class PhoneDataSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int code;
    private String number;

    public PhoneDataSet() {
    }

    public PhoneDataSet(String number, int code) {
        this.number = number;
        this.code = code;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhoneDataSet that = (PhoneDataSet) o;

        if (id != that.id) return false;
        if (code != that.code) return false;
        return number != null ? number.equals(that.number) : that.number == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + code;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        return result;
    }
}
