package ru.otus.l7.atm;

import ru.otus.l7.Memento;
import ru.otus.l7.atm.cells.Cell;
import ru.otus.l7.atm.cells.ICell;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ATM {
    Memento memento;
    private ICell firstCell;

    public ATM() {
        initCells();
    }

    private void initCells() {
        List<ICell> cells = Arrays.asList(new Cell(1), new Cell(10), new Cell(50), new Cell(100), new Cell(1000), new Cell(5000));
        Collections.sort(cells);
        firstCell = cells.get(0);
        linkCells(cells);
    }

    public void put(Currency currency) {
        firstCell.put(currency);
    }

    private void linkCells(List<ICell> cells) {
        Iterator<ICell> iterator = cells.iterator();
        ICell cellA = iterator.next();
        while (iterator.hasNext()) {
            ICell cellB = iterator.next();
            cellA.setNext(cellB);
            cellA = cellB;
        }
    }

    public int getBalance() {
        return firstCell.getBalance();
    }

    public List<Currency> withdraw(int requested) {
        return firstCell.withdraw(requested);
    }

    public void saveToMemento() {
        memento = new Memento(firstCell.getState());
    }

    public void restoreFromMemento() {
        this.firstCell = memento.getSavedState();
    }
}
