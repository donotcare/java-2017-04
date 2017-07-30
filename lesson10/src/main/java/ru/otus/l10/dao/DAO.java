package ru.otus.l10.dao;


import ru.otus.l10.model.DataSet;

import java.util.List;


public interface DAO<T extends DataSet> {
    List<T> readAll();

    T read(long id);

    void save(T dataSet);
}
