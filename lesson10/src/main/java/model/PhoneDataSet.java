package model;

import javax.persistence.*;

@Entity
@Table(name = "Phones")
public class PhoneDataSet extends DataSet {

    private int code;
    private String number;

    public PhoneDataSet() {
    }

    public PhoneDataSet(String number, int code) {
        this.number = number;
        this.code = code;
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

        if (code != that.code) return false;
        return number != null ? number.equals(that.number) : that.number == null;

    }

    @Override
    public int hashCode() {
        int result = code;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        return result;
    }
}
