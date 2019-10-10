package Repository;

import BO.Agence;
import Connection.PersistanteManager;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgenceRepository implements IRepository<Agence>, AutoCloseable {

    private static final String INSERT_QUERY = "INSERT INTO agence (code, adresse) VALUES(?,?)";
    private static final String UPDATE_QUERY = "UPDATE agence SET code = ?, adresse = ? WHERE id = ?";
    private static final String REMOVE_QUERY = "DELETE FROM agence WHERE id = ?";
    private static final String FIND__BY_CODE_QUERY = "SELECT * FROM agence WHERE code = ?";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM agence WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM agence";

    @Override
    public ArrayList<Agence> getAll() throws SQLException, IOException, ClassNotFoundException {

        ArrayList<Agence> listAgence = new ArrayList<>();
        Connection connection = PersistanteManager.getConnection();
        if ( connection != null ) {
            try ( PreparedStatement ps = connection.prepareStatement( FIND_ALL_QUERY ) ) {
                try ( ResultSet rs = ps.executeQuery() ) {
                    if ( rs.next() ) {
                        Agence agence = new Agence();
                        agence.setId( rs.getInt( "id" ) );
                        agence.setCode( rs.getString( "code" ) );
                        agence.setAdresse( rs.getString( "adresse" ) );
                        listAgence.add(agence);
                    }
                }
            }
        }
        return listAgence;
    }

    public Agence getOneByCode(String code ) throws SQLException, IOException, ClassNotFoundException {

        Agence agence = null;
        Connection connection = PersistanteManager.getConnection();
        if ( connection != null ) {
            try ( PreparedStatement ps = connection.prepareStatement( FIND__BY_CODE_QUERY ) ) {
                ps.setString( 1, code );
                try ( ResultSet rs = ps.executeQuery() ) {
                    if ( rs.next() ) {
                        agence = new Agence();
                        agence.setId( rs.getInt( "id" ) );
                        agence.setCode( rs.getString( "code" ) );
                        agence.setAdresse( rs.getString( "adresse" ) );
                    }
                }
            }
        }
        return agence;

    }

    public Agence getOneById(int id) throws SQLException, IOException, ClassNotFoundException {

        Agence agence = null;
        Connection connection = PersistanteManager.getConnection();
        if ( connection != null ) {
            try ( PreparedStatement ps = connection.prepareStatement( FIND_BY_ID_QUERY ) ) {
                ps.setInt( 1, id );
                try ( ResultSet rs = ps.executeQuery() ) {
                    if ( rs.next() ) {
                        agence = new Agence();
                        agence.setId( rs.getInt( "id" ) );
                        agence.setCode( rs.getString( "code" ) );
                        agence.setAdresse( rs.getString( "adresse" ) );
                    }
                }
            }
        }
        return agence;
    }

    @Override
    public void Add(Agence _object) throws SQLException, IOException, ClassNotFoundException {

        Connection connection = PersistanteManager.getConnection();
        if ( connection != null ) {
            try ( PreparedStatement ps = connection.prepareStatement( INSERT_QUERY ) ) {
                ps.setString( 1, _object.getCode() );
                ps.setString( 2, _object.getAdresse() );
                try ( ResultSet rs = ps.executeQuery() ) {
                    if ( rs.next() ) {
                        _object.setId( rs.getInt( 1 ) );
                    }
                }
            }
        }
    }

    @Override
    public void Remove(Agence _object) throws SQLException, IOException, ClassNotFoundException {

        Connection connection = PersistanteManager.getConnection();
        if ( connection != null ) {
            try ( PreparedStatement ps = connection.prepareStatement( REMOVE_QUERY ) ) {
                ps.setInt( 1, _object.getId());

            }
        }

    }

    @Override
    public void Update(Agence _object) throws SQLException, IOException, ClassNotFoundException {

        Connection connection = PersistanteManager.getConnection();
        if ( connection != null ) {
            try ( PreparedStatement ps = connection.prepareStatement( UPDATE_QUERY ) ) {
                ps.setString( 1, _object.getCode() );
                ps.setString( 2, _object.getAdresse() );
                ps.setInt(3, _object.getId());
                try ( ResultSet rs = ps.executeQuery() ) {
                    if ( rs.next() ) {
                        _object.setId( rs.getInt( 1 ) );
                    }
                }
            }
        }

    }

    @Override
    public void close() throws Exception { }

}
