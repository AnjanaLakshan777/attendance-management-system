package edu.self.sams.dao;

import java.util.ArrayList;

public interface CrudDao <T,ID> extends SuperDao {
    boolean save(T t) throws Exception;
    boolean update(T t) throws Exception;
    boolean delete(ID id) throws Exception;
    T get(ID  id) throws Exception;
    ArrayList<T> getAll() throws Exception;
}