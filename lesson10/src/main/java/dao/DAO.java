package dao;


import model.DataSet;

import java.util.List;


public interface DAO<T extends DataSet> {
    List<T> readAll();

    T read(long id);

    void save(T dataSet);
}
