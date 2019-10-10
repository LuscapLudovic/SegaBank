package Repository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IRepository <T>{

    ArrayList<T> getAll() throws SQLException, IOException, ClassNotFoundException;

    void Add(T _object) throws SQLException, IOException, ClassNotFoundException;

    void Remove(T _object) throws SQLException, IOException, ClassNotFoundException;

    void Update(T _object) throws SQLException, IOException, ClassNotFoundException;
}
