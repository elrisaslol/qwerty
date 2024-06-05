package org.example.model.dao;

import java.io.Closeable;
import java.sql.SQLException;
import java.util.List;

public interface DAO<T,K> extends Closeable {
    T save(T entity);
    void delete(T entity) throws SQLException;
    T findById(K key);
    List<T> findAll();
    List<T> findAllNonEditable();
}
