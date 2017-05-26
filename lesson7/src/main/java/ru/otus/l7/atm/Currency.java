package ru.otus.l7.atm;

public class Currency {
    private final int nominal;
    private int count;

    public Currency(int nominal, int count) {
        this.nominal = nominal;
        this.count = count;
    }

    public boolean add(Currency currency) {
        if (currency.nominal != nominal)
            return false;
        count += currency.count;
        return true;
    }

    public Currency withdraw(int requested) {
        int withdrawCount = Math.min(requested / nominal, count);
        count -= withdrawCount;
        return new Currency(nominal, withdrawCount);
    }

    public int getCount() {
        return count;
    }

    public int getNominal() {
        return nominal;
    }

    public int getBalance() {
        return nominal * count;
    }

    @Override
    public String toString() {
        return "ru.otus.l5.Currency{" +
                "nominal=" + nominal +
                ", count=" + count +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Currency currency = (Currency) o;

        return nominal == currency.nominal && count == currency.count;

    }

    @Override
    public int hashCode() {
        int result = nominal;
        result = 31 * result + count;
        return result;
    }
}
