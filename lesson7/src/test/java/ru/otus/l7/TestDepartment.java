package ru.otus.l7;

import org.junit.Assert;
import org.junit.Test;
import ru.otus.l7.atm.ATM;
import ru.otus.l7.atm.Currency;

public class TestDepartment {

    @Test
    public void balanceSimple() {
        Department department = new Department();
        ATM atm = new ATM();
        atm.put(new Currency(1, 1));
        atm.put(new Currency(10, 11));
        atm.put(new Currency(100, 1));
        department.addAtm(atm);

        atm = new ATM();
        atm.put(new Currency(10, 10));
        department.addAtm(atm);

        Assert.assertEquals(311, department.getBalance());
    }

    @Test
    public void restoreState() {
        Department department = new Department();
        ATM atmFirst = new ATM();
        atmFirst.put(new Currency(1, 1));
        department.addAtm(atmFirst);

        ATM atmSecond = new ATM();
        department.addAtm(atmSecond);

        department.saveState();

        atmFirst.put(new Currency(1, 1));
        atmSecond.put(new Currency(1, 10));

        department.restore();

        Assert.assertEquals(1, department.getBalance());
    }
}
