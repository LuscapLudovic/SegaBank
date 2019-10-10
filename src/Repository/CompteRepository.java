package Repository;

import BO.Agence;
import BO.Compte;
import BO.TypeCompte;
import Connection.PersistanteManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CompteRepository implements IRepository<Compte> {


    private static final String INSERT_QUERY = "INSERT INTO compte (solde, decouvert, tauxInteret, typeCompte, agence) VALUES(?,?,?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE compte SET solde = ?, decouvert = ?, tauxInteret = ?, typeCompte = ?, agence = ? WHERE id = ?";
    private static final String REMOVE_QUERY = "DELETE FROM compte WHERE id = ?";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM compte WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM compte";

    @Override
    public ArrayList<Compte> getAll() throws SQLException, IOException, ClassNotFoundException {

        ArrayList listCompte = new ArrayList();
        Connection connection = PersistanteManager.getConnection();
        if ( connection != null ) {
            try ( PreparedStatement ps = connection.prepareStatement( FIND_ALL_QUERY ) ) {
                try ( ResultSet rs = ps.executeQuery() ) {
                    if ( rs.next() ) {
                        Compte compte = new Compte();
                        compte.setId( rs.getInt( "id" ) );
                        compte.setSolde( rs.getDouble( "solde" ) );
                        compte.setDecouvert( rs.getDouble( "decouvert") );
                        compte.setTauxInteret(rs.getDouble( "tauxInteret" ));
                        compte.setTypeCompte(
                                new TypeCompteRepository().getOneById( rs.getInt("typeCompte") )
                        );
                        compte.setAgence(
                                (new AgenceRepository().getOneById( rs.getInt("agence") ) )
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
        Connection connection = PersistanteManager.getConnection();
        if ( connection != null ) {
            try ( PreparedStatement ps = connection.prepareStatement( FIND_BY_ID_QUERY ) ) {
                ps.setInt( 1, id );
                try ( ResultSet rs = ps.executeQuery() ) {
                    if ( rs.next() ) {
                        compte = new Compte();
                        compte.setId( rs.getInt( "id" ) );
                        compte.setSolde( rs.getDouble( "solde" ) );
                        compte.setDecouvert( rs.getDouble( "decouvert" ) );
                        compte.setTauxInteret( rs.getDouble( "tauxInteret" ) );
                        compte.setTypeCompte(
                                new TypeCompteRepository().getOneById( rs.getInt("typeCompte") )
                        );
                        compte.setAgence(
                                (new AgenceRepository().getOneById( rs.getInt("agence") ) )
                        );
                    }
                }
            }
        }
        return compte;
    }

    @Override
    public void Add(Compte _object) throws SQLException, IOException, ClassNotFoundException {


        Connection connection = PersistanteManager.getConnection();
        if ( connection != null ) {
            try ( PreparedStatement ps = connection.prepareStatement( INSERT_QUERY ) ) {
                ps.setDouble( 1, _object.getSolde() );
                ps.setDouble( 2, _object.getDecouvert() );
                ps.setDouble( 2, _object.getTauxInteret() );
                ps.setInt( 2, _object.getTypeCompte().getId() );
                ps.setInt( 2, _object.getAgence().getId() );
                try ( ResultSet rs = ps.executeQuery() ) {
                    if ( rs.next() ) {
                        _object.setId( rs.getInt( 1 ) );
                    }
                }
            }
        }

    }

    @Override
    public void Remove(Compte _object) throws SQLException, IOException, ClassNotFoundException {

        Connection connection = PersistanteManager.getConnection();
        if ( connection != null ) {
            try ( PreparedStatement ps = connection.prepareStatement( REMOVE_QUERY ) ) {
                ps.setInt( 1, _object.getId());

            }
        }

    }

    @Override
    public void Update(Compte _object) throws SQLException, IOException, ClassNotFoundException {

        Connection connection = PersistanteManager.getConnection();
        if ( connection != null ) {
            try ( PreparedStatement ps = connection.prepareStatement( UPDATE_QUERY ) ) {
                ps.setDouble( 1, _object.getSolde() );
                ps.setDouble( 2, _object.getDecouvert() );
                ps.setDouble( 2, _object.getTauxInteret() );
                ps.setInt( 2, _object.getTypeCompte().getId() );
                ps.setInt( 2, _object.getAgence().getId() );
                try ( ResultSet rs = ps.executeQuery() ) {
                    if ( rs.next() ) {
                        _object.setId( rs.getInt( 1 ) );
                    }
                }
            }
        }

    }

}
