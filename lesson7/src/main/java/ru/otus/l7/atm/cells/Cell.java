package ru.otus.l7.atm.cells;

import ru.otus.l7.atm.Currency;

import java.util.ArrayList;
import java.util.List;

public class Cell implements ICell {
    private Currency currency;
    private ICell next = EmptyCell.getCell();

    public Cell(int nominal) {
        currency = new Currency(nominal, 0);
    }

    public void setNext(ICell next) {
        this.next = next;
    }

    public void put(Currency currency) {
        if (!this.currency.add(currency)) {
            next.put(currency);
        }
    }

    public List<Currency> withdraw(int requested) {
        Currency withdrawn = currency.withdraw(requested);
        int left = requested - withdrawn.getBalance();
        List<Currency> withdrawnCurrencies = left != 0 ? next.withdraw(left) : new ArrayList<>();
        if (requested != left)
            withdrawnCurrencies.add(withdrawn);
        return withdrawnCurrencies;
    }

    public int getNominal() {
        return currency.getNominal();
    }

    public int getBalance() {
        return currency.getBalance() + next.getBalance();
    }

    @Override
    public int compareTo(ICell o) {
        if (currency.getNominal() > o.getNominal())
            return -1;
        if (currency.getNominal() < o.getNominal())
            return 1;
        return 0;
    }

    public ICell getState() {
        Cell state = new Cell(currency.getNominal());
        state.setNext(next.getState());
        state.put(currency);
        return state;
    }
}
