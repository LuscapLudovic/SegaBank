package Repository;

import BO.Operation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class OperationRepository implements IRepository<Operation>, AutoCloseable {

    @Override
    public ArrayList<Operation> getAll() {
        return null;
    }

    public Operation getOneById() {
        return null;
    }

    @Override
    public void Add(Operation _object) {

    }

    @Override
    public void Remove(Operation _object) {

    }

    @Override
    public void Update(Operation _object) throws SQLException, IOException, ClassNotFoundException {

    }

    @Override
    public void close() throws Exception {
    }
}
