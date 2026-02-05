package edu.self.sams.dao;

import java.util.ArrayList;

public interface CrudDao <T,Username> extends SuperDao {
    boolean save(T t) throws Exception;
    boolean update(T t) throws Exception;
    boolean delete(Username username) throws Exception;
    T get(Username  username) throws Exception;
    ArrayList<T> getAll() throws Exception;
}
