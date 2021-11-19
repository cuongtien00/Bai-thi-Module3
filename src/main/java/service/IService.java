package service;

import java.sql.SQLException;
import java.util.List;

public interface IService<T> {
    boolean insert(T t)throws SQLException;
    boolean update(T t)throws SQLException;
    boolean delete(int id)throws SQLException;
    T findById(int id)throws SQLException;
    List<T> findAll()throws SQLException;
}
