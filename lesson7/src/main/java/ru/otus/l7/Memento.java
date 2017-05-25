package ru.otus.l7;

import ru.otus.l7.atm.cells.ICell;

public class Memento {
    private final ICell state;

    public Memento(ICell stateToSave) {
        state = stateToSave;
    }

    public ICell getSavedState() {
        return state;
    }
}
