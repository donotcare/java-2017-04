package ru.otus.l10.service;

import ru.otus.l10.model.DataSet;

import java.util.List;

public interface DBService<T extends DataSet> {
    void save(T dataSet);
    T read(long id);
    List<T> readAll();
}
