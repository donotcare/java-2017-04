package ru.otus.l7;

import ru.otus.l7.atm.ATM;

import java.util.ArrayList;
import java.util.List;

public class Department {
    private List<ATM> atms = new ArrayList<>();

    public void addAtm(ATM atm) {
        atms.add(atm);
    }

    public int getBalance() {
        return atms.stream().mapToInt(ATM::getBalance).sum();
    }

    public void restore() {
        atms.forEach(ATM::restoreFromMemento);
    }

    public void saveState() {
        atms.forEach(ATM::saveToMemento);
    }
}
