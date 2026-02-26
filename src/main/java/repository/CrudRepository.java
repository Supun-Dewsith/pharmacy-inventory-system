package repository;

import java.sql.SQLException;
import java.util.List;

public interface CrudRepository<T,ID> extends SuperRepository{
    boolean create(T t) throws SQLException;
    boolean update(T t) throws SQLException;
    boolean deleteById(ID id) throws SQLException;
    T getById(ID id) throws SQLException;
    List<T> getAll() throws SQLException;
}
