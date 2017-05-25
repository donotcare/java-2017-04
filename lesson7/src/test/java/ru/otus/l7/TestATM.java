package ru.otus.l7;

import org.junit.Assert;
import org.junit.Test;
import ru.otus.l7.atm.ATM;
import ru.otus.l7.atm.Currency;

import java.util.Arrays;
import java.util.List;

public class TestATM {

    @Test
    public void balanceSimple() {
        ATM atm = new ATM();
        atm.put(new Currency(1, 1));
        atm.put(new Currency(10, 11));
        atm.put(new Currency(100, 1));
        Assert.assertEquals(211, atm.getBalance());
    }

    @Test
    public void withdrawSimple() {
        List<Currency> expected = Arrays.asList( new Currency(1, 1), new Currency(10, 9));

        ATM atm = new ATM();
        atm.put(new Currency(1, 1));
        atm.put(new Currency(10, 10));

        List<Currency> withdrawn = atm.withdraw(91);

        Assert.assertEquals(expected, withdrawn);
        Assert.assertEquals(10, atm.getBalance());
    }

    @Test(expected = RuntimeException.class)
    public void wrongCurrency() {
        ATM atm = new ATM();
        atm.put(new Currency(9, 1));
    }

    @Test(expected = RuntimeException.class)
    public void withdrawFailed() {
        ATM atm = new ATM();
        atm.put(new Currency(1, 1));
        atm.put(new Currency(100, 1));

        List<Currency> withdrawn = atm.withdraw(2);
    }
}
