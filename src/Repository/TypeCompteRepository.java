package Repository;

import BO.TypeCompte;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class TypeCompteRepository implements IRepository<TypeCompte>, AutoCloseable {

    @Override
    public ArrayList<TypeCompte> getAll() {
        return null;
    }

    public TypeCompte getOne() {
        return null;
    }

    public TypeCompte getOneById(int typeCompte) {
        return null;
    }

    @Override
    public void Add(TypeCompte _object) {

    }

    @Override
    public void Remove(TypeCompte _object) {

    }

    @Override
    public void Update(TypeCompte _object) throws SQLException, IOException, ClassNotFoundException {

    }

    @Override
    public void close() throws Exception {

    }
}
