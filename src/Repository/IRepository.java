package Repository;

import java.util.ArrayList;

public interface IRepository <T>{

    ArrayList<T> getAll();

    T getOne();

    void Add(T _object);

    void Remove(T _object);
}
