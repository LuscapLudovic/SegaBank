package Repository;

import BO.TypeCompte;
import Connection.PersistanteManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TypeCompteRepository implements IRepository<TypeCompte>{

    private static Connection connection;

    private static final String INSERT_QUERY = "INSERT INTO typeCompte (libelle) VALUES(?)";
    private static final String UPDATE_QUERY = "UPDATE typeCompte SET libelle = ? WHERE id = ?";
    private static final String REMOVE_QUERY = "DELETE FROM typeCompte WHERE id = ?";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM typeCompte WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM typeCompte";

    public TypeCompteRepository(Connection _connection) {
        connection = _connection;
    }

    public TypeCompteRepository() throws SQLException, IOException, ClassNotFoundException {
        connection = PersistanteManager.getConnection();
    }

    @Override
    public ArrayList<TypeCompte> getAll () throws SQLException, IOException, ClassNotFoundException
    {
        ArrayList<TypeCompte> listTypeCompte = new ArrayList<>();

        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(FIND_ALL_QUERY)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        TypeCompte typeCompte = new TypeCompte();
                        typeCompte.setId(rs.getInt("id"));
                        typeCompte.setLibelle(rs.getString("libelle"));
                        listTypeCompte.add(typeCompte);
                    }
                }
            }
        }
        return listTypeCompte;
    }

    public TypeCompte getOneById(int id) throws SQLException, IOException, ClassNotFoundException {
        TypeCompte typeCompte = null;

        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(FIND_BY_ID_QUERY)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        typeCompte = new TypeCompte();
                        typeCompte.setId(rs.getInt("id"));
                        typeCompte.setLibelle(rs.getString("libelle"));
                    }
                }
            }
        }
        return typeCompte;
    }

    @Override
    public void Add(TypeCompte _object) throws SQLException, IOException, ClassNotFoundException {


            if (connection != null) {
                try (PreparedStatement ps = connection.prepareStatement(INSERT_QUERY)) {
                    ps.setString(1, _object.getLibelle());
                    ps.executeUpdate();
                }
            }

    }

    @Override
    public void Remove(TypeCompte _object) throws SQLException, IOException, ClassNotFoundException {


            if (connection != null) {
                try (PreparedStatement ps = connection.prepareStatement(REMOVE_QUERY)) {
                    ps.setInt(1, _object.getId());
                    ps.executeUpdate();
                }
            }
    }

    @Override
    public void Update(TypeCompte _object) throws SQLException, IOException, ClassNotFoundException {

            if (connection != null) {
                try (PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY)) {
                    ps.setString(1, _object.getLibelle());
                    ps.setInt(2, _object.getId());
                    ps.executeUpdate();
                }
            }

    }

    @Override
    public void close() throws Exception { }
}
