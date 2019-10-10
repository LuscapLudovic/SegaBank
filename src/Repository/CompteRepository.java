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


    private static final String INSERT_QUERY = "INSERT INTO compte (code, adresse) VALUES(?,?)";
    private static final String UPDATE_QUERY = "UPDATE compte SET code = ?, adresse = ? WHERE id = ?";
    private static final String REMOVE_QUERY = "DELETE FROM compte WHERE id = ?";
    private static final String FIND_QUERY = "SELECT * FROM compte WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM compte";

    @Override
    public ArrayList<Compte> getAll() throws SQLException, IOException, ClassNotFoundException {

        ArrayList<Compte> listCompte = new ArrayList();
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

    public Compte getOneById() {
        return null;
    }

    @Override
    public void Add(Compte _object) {

    }

    @Override
    public void Remove(Compte _object) {

    }

    @Override
    public void Update(Compte _object) throws SQLException, IOException, ClassNotFoundException {

    }

}
