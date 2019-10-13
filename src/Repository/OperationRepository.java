package Repository;

import BO.Compte;
import BO.Operation;
import Connection.PersistanteManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OperationRepository implements IRepository<Operation>{

    private static final String INSERT_QUERY = "INSERT INTO operation (compteDebId, compteBenefId, montant) VALUES(?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE operation SET compteDebId = ?, compteBenefId = ?, montant = ? WHERE id = ?";
    private static final String REMOVE_QUERY = "DELETE FROM operation WHERE id = ?";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM operation WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM operation";
    private static final String FIND_BY_COMPTE = "SELECT operation.id, compteDebId, compteBenefId, montant from operation inner join compte on operation.deb = compte.id where compte.id = ?";

    @Override
    public ArrayList<Operation> getAll() throws SQLException, IOException, ClassNotFoundException {
        ArrayList<Operation> listOperation = new ArrayList<>();
        try(Connection connection = PersistanteManager.getConnection()) {
            if (connection != null) {
                try (PreparedStatement ps = connection.prepareStatement(FIND_ALL_QUERY)) {
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            Operation operation = new Operation();
                            operation.setId(rs.getInt("id"));
                            operation.setCompteDeb(
                                    (new CompteRepository().getOneById(rs.getInt("compteDebId")))
                            );
                            operation.setCompteBenef(
                                    (new CompteRepository().getOneById(rs.getInt("compteBenefId")))
                            );
                            operation.setMontant(rs.getDouble("montant"));
                            listOperation.add(operation);
                        }
                    }
                }
            }
        }
        return listOperation;
    }

    public Operation getOneById(Integer id) throws SQLException, IOException, ClassNotFoundException {

        Operation operation = null;
        try(Connection connection = PersistanteManager.getConnection()){
            if ( connection != null ) {
                try ( PreparedStatement ps = connection.prepareStatement( FIND_BY_ID_QUERY ) ) {
                    ps.setInt( 1, id );
                    try ( ResultSet rs = ps.executeQuery() ) {
                        if ( rs.next() ) {
                            operation = new Operation();
                            operation.setId( rs.getInt( "id" ) );
                            operation.setCompteDeb(
                                    (new CompteRepository().getOneById( rs.getInt("compteDebId") ))
                            );
                            operation.setCompteBenef(
                                    (new CompteRepository().getOneById( rs.getInt("compteBenefId") ) )
                            );
                            operation.setMontant( rs.getDouble("montant") );
                        }
                    }
                }
            }
        }
        return operation;

    }

    public ArrayList<Operation> getByCompte(Compte compte) throws SQLException, IOException, ClassNotFoundException {

        ArrayList<Operation> listOperation = new ArrayList<>();
        try(Connection connection = PersistanteManager.getConnection()) {
            if (connection != null) {
                try (PreparedStatement ps = connection.prepareStatement(FIND_BY_COMPTE)) {
                    ps.setInt(1, compte.getId());
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            Operation operation = new Operation();
                            operation.setId(rs.getInt("id"));
                            operation.setCompteDeb(
                                    (new CompteRepository().getOneById(rs.getInt("compteDebId")))
                            );
                            operation.setCompteBenef(
                                    (new CompteRepository().getOneById(rs.getInt("compteBenefId")))
                            );
                            operation.setMontant(rs.getDouble("montant"));
                            listOperation.add(operation);
                        }
                    }
                }
            }
        }
        return listOperation;
    }

    @Override
    public void Add(Operation _object) throws SQLException, IOException, ClassNotFoundException {

        try(Connection connection = PersistanteManager.getConnection()) {
            if (connection != null) {
                try (PreparedStatement ps = connection.prepareStatement(INSERT_QUERY)) {
                    ps.setInt(1, _object.getCompteDeb().getId());
                    ps.setInt(2, _object.getCompteBenef().getId());
                    ps.setDouble(3, _object.getMontant());
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            _object.setId(rs.getInt(1));
                        }
                    }
                }
            }
        }

    }

    @Override
    public void Remove(Operation _object) throws SQLException, IOException, ClassNotFoundException {

        try(Connection connection = PersistanteManager.getConnection()) {
            if (connection != null) {
                try (PreparedStatement ps = connection.prepareStatement(REMOVE_QUERY)) {
                    ps.setInt(1, _object.getId());
                    ps.executeQuery();
                }
            }
        }
    }

    @Override
    public void Update(Operation _object) throws SQLException, IOException, ClassNotFoundException {

        try(Connection connection = PersistanteManager.getConnection()){
            if ( connection != null ) {
                try (PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY)) {
                    ps.setInt(1, _object.getCompteDeb().getId());
                    ps.setInt(2, _object.getCompteBenef().getId());
                    ps.setDouble(3, _object.getMontant());
                    ps.setInt(4, _object.getId());
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            _object.setId(rs.getInt(1));
                        }
                    }
                }
            }
        }


    }

    @Override
    public void close() throws Exception { }
}
