package dao;


import java.util.List;


public interface DAO<T> {
    List<T> readAll();

    T read(long id);

    void save(T dataSet);
}
