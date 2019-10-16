package Repository;

import BO.Compte;
import Connection.PersistanteManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CompteRepository implements IRepository<Compte> {


    private static Connection connection;

    private static final String INSERT_QUERY = "INSERT INTO compte (solde, decouvert, tauxInteret, typeCompte_id, agence_id) VALUES(?,?,?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE compte SET solde = ?, decouvert = ?, tauxInteret = ?, typeCompte_id = ?, agence_id = ? WHERE id = ?";
    private static final String REMOVE_QUERY = "DELETE FROM compte WHERE id = ?";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM compte WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM compte";

    public CompteRepository(Connection _connection) {
        connection = _connection;
    }

    public CompteRepository() throws SQLException, IOException, ClassNotFoundException {
        connection = PersistanteManager.getConnection();
    }

    @Override
    public ArrayList<Compte> getAll() throws SQLException, IOException, ClassNotFoundException {

        ArrayList<Compte> listCompte = new ArrayList<>();

            if (connection != null) {
                try (PreparedStatement ps = connection.prepareStatement(FIND_ALL_QUERY)) {
                    try (ResultSet rs = ps.executeQuery()) {
                        while(rs.next()) {
                            Compte compte = new Compte();
                            compte.setId(rs.getInt("id"));
                            compte.setSolde(rs.getDouble("solde"));
                            compte.setDecouvert(rs.getDouble("decouvert"));
                            compte.setTauxInteret(rs.getDouble("tauxInteret"));
                            compte.setTypeCompte(
                                    new TypeCompteRepository(connection).getOneById(rs.getInt("typeCompte_id"))
                            );
                            compte.setAgence(
                                    (new AgenceRepository(connection).getOneById(rs.getInt("agence_id")))
                            );
                            listCompte.add(compte);
                        }
                    }
                }
            }

        return listCompte;
    }

    public Compte getOneById(Integer id) throws SQLException, IOException, ClassNotFoundException {

        Compte compte = null;

            if (connection != null) {
                try (PreparedStatement ps = connection.prepareStatement(FIND_BY_ID_QUERY)) {
                    ps.setInt(1, id);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            compte = new Compte();
                            compte.setId(rs.getInt("id"));
                            compte.setSolde(rs.getDouble("solde"));
                            compte.setDecouvert(rs.getDouble("decouvert"));
                            compte.setTauxInteret(rs.getDouble("tauxInteret"));
                            compte.setTypeCompte(
                                    new TypeCompteRepository().getOneById(rs.getInt("typeCompte_id"))
                            );
                            compte.setAgence(
                                    (new AgenceRepository().getOneById(rs.getInt("agence_id")))
                            );
                        }
                    }
                }
            }

        return compte;
    }

    @Override
    public void Add(Compte _object) throws SQLException, IOException, ClassNotFoundException {



            if ( connection != null ) {
                try ( PreparedStatement ps = connection.prepareStatement( INSERT_QUERY ) ) {
                    ps.setDouble( 1, _object.getSolde() );
                    ps.setDouble( 2, _object.getDecouvert() );
                    ps.setDouble( 3, _object.getTauxInteret() );
                    ps.setInt( 4, _object.getTypeCompte().getId() );
                    ps.setInt( 5, _object.getAgence().getId() );
                    ps.executeUpdate();
                }
            }


    }

    @Override
    public void Remove(Compte _object) throws SQLException, IOException, ClassNotFoundException {


            if (connection != null) {
                try (PreparedStatement ps = connection.prepareStatement(REMOVE_QUERY)) {
                    ps.setInt(1, _object.getId());
                    ps.executeUpdate();
                }
            }

    }

    @Override
    public void Update(Compte _object) throws SQLException, IOException, ClassNotFoundException {


            if (connection != null) {
                try (PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY)) {
                    ps.setDouble(1, _object.getSolde());
                    ps.setDouble(2, _object.getDecouvert());
                    ps.setDouble(3, _object.getTauxInteret());
                    ps.setInt(4, _object.getTypeCompte().getId());
                    ps.setInt(5, _object.getAgence().getId());
                    ps.setInt(6, _object.getId());
                    ps.executeUpdate();
                }
            }

    }

    @Override
    public void close() throws Exception { }
}
