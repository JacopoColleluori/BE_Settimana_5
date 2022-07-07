package it.InfrazioneDao;

import PoliziaCRUD.ConnectionFactory;
import it.AutoDao.Auto;
import it.AutoDao.AutoDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;

public class InfrazioneDao {
   Logger logger= LoggerFactory.getLogger(InfrazioneDao.class);

   public boolean inserisciInfrazione(Infrazione infrazione) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement ps;
        int i = 0;

        try {
            String insert = "INSERT INTO infrazione (id,datan,tipo,importo,targa_auto) VALUES (?,?,?,?,?)";
            ps = con.prepareStatement(insert);


            ps.setInt(1, infrazione.getId());
            ps.setDate(2, infrazione.getDatan());
            ps.setString(3, infrazione.getTipo());
            ps.setDouble(4, infrazione.getImporto());
            ps.setString(5,infrazione.getTarga_auto());

            i = ps.executeUpdate();
           logger.info("Inserimento avvenuto correttamente!");

        } catch (SQLException e) {
           logger.error("Errore Inserimento!");
            e.printStackTrace();
        }

        try {
            con.close();
        } catch (Exception e) {
        }


       return i > 0;
    }

    public void StampaDatiInfrazioni() {
        Connection con = ConnectionFactory.getConnection();
        Statement st;
        ResultSet rs = null;
        ArrayList<Infrazione> listaInfrazioni = null;

        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM infrazione");

            listaInfrazioni = new ArrayList<>();

            while (rs.next()) {
                Infrazione infrazione = new Infrazione();
                infrazione.setId(rs.getInt("id"));
                infrazione.setDatan(rs.getDate("datan"));
                infrazione.setTipo(rs.getString("tipo"));
                infrazione.setImporto(rs.getDouble("importo"));
                infrazione.setTarga_auto(rs.getString("targa_auto"));

                listaInfrazioni.add(infrazione);

            }

            System.out.println("getAll Avvenuto!");
        } catch (SQLException e) {
            System.out.println("Errore getAll");
            e.printStackTrace();
        }

        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
        }

        try {
            con.close();
        } catch (Exception e) {
        }

        AutoDao autoDao=new AutoDao();
        assert listaInfrazioni != null;
        for (Infrazione infrazione : listaInfrazioni) {
            Auto auto;
            auto=autoDao.cercaAuto(infrazione.getTarga_auto());
           logger.info("\nInfrazione n. " + infrazione.getId() + " \nTipo infrazione: " + infrazione.getTipo()
                    + "\nData infrazione: " + infrazione.getDatan() + " \nImporto infrazione: " + infrazione.getImporto()+" €\n" +
                   "Targa dell'auto incriminata: "+auto.getTarga()+" \nModello auto incriminata: "+auto.getModello()+
                   "\nMarca auto incriminata: "+auto.getMarca()+"\n");
        }

    }

    public boolean eliminaInfrazione(int id) {

            Connection con = ConnectionFactory.getConnection();
            Statement st;
            int i = 0;
            try {
                String delete = "DELETE FROM infrazione WHERE id = " + id;
                st = con.createStatement();
                i = st.executeUpdate(delete);
               logger.info("Eliminazione Avvenuta");
            } catch (SQLException e) {
               logger.error("Errore nell'eleminazione ");
                e.printStackTrace();
            }
            try {
                con.close();
            } catch (Exception e) {
            }
        return i > 0;

    }


}
