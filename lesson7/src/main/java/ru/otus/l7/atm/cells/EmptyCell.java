package ru.otus.l7.atm.cells;


import ru.otus.l7.atm.Currency;

import java.util.List;

public class EmptyCell implements ICell {
    private static ICell emptyCell = new EmptyCell();

    private EmptyCell() {
    }

    @Override
    public void setNext(ICell next) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void put(Currency currency) {
        throw new RuntimeException("No cell for this nominal");
    }

    @Override
    public List<Currency> withdraw(int requested) {
        throw new RuntimeException("Has no currency to withdraw");
    }

    @Override
    public int getNominal() {
        return 0;
    }

    @Override
    public int getBalance() {
        return 0;
    }

    @Override
    public ICell getState() {
        return this;
    }

    @Override
    public int compareTo(ICell o) {
        return 0;
    }

    public static ICell getCell() {
        return emptyCell;
    }

}
