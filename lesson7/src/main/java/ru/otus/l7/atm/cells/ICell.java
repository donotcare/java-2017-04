package ru.otus.l7.atm.cells;


import ru.otus.l7.atm.Currency;

import java.util.List;

public interface ICell extends Comparable<ICell> {

    void setNext(ICell next);

    void put(Currency currency);

    List<Currency> withdraw(int requested);

    int getNominal();

    int getBalance();

    ICell getState();
}
