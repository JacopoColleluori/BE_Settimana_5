package it.AutoDao;

import PoliziaCRUD.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.sql.*;
import java.util.ArrayList;

public class AutoDao {
Logger logger= LoggerFactory.getLogger(AutoDao.class);
    public boolean inserisciAuto(Auto auto){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement ps;
        int i = 0;

        try {
            String insert = "INSERT INTO auto (Targa,Marca,Modello) VALUES (?,?,?)";
            ps = con.prepareStatement(insert);


            ps.setString(1, auto.getTarga());
            ps.setString(2, auto.getMarca());
            ps.setString(3, auto.getModello());

            i = ps.executeUpdate();
           logger.info("Inserimento avvenuto correttamente");

        } catch (SQLException e) {
            logger.error("Errore Inserimento");
            e.printStackTrace();
        }

        try {con.close();}
        catch (Exception e) {}


        return i > 0;
    }

    public ArrayList<Auto> getAllAuto(){
        Connection con = ConnectionFactory.getConnection();
        Statement st;
        ResultSet rs = null;
        ArrayList<Auto> listaAuto = null;
        try{
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM auto");

            listaAuto = new ArrayList<>();

            while (rs.next()){
                Auto auto = new Auto();
                auto.setTarga(rs.getString("Targa"));
                auto.setMarca(rs.getString("Marca"));
                auto.setModello(rs.getString("Modello"));

                listaAuto.add(auto);
            }

            logger.info("getAll avvenuto con successo");
        }
        catch (SQLException e){
         logger.error("Errore getAll");
            e.printStackTrace();
        }

        try {
            if (rs != null) {
                rs.close();
            }
        }
        catch (Exception e) {}

        try {con.close();}
        catch (Exception e) {}

        return listaAuto;
    }

    public Auto cercaAuto(String targa){
        Connection con = ConnectionFactory.getConnection();
        ResultSet rs = null;
        Auto auto = new Auto();
        try{
            PreparedStatement st = con.prepareStatement("SELECT * FROM auto WHERE targa like ?");

            st.setString(1,targa);

            rs = st.executeQuery();
            while (rs.next()) {
                auto.setTarga(rs.getString("Targa"));
                auto.setMarca(rs.getString("Marca"));
                auto.setModello(rs.getString("Modello"));
            }
           logger.info("Ricerca avvenuta con successo");
        }
        catch (SQLException e){
           logger.error("targa non trovata");
            e.printStackTrace();
        }



        try {
            if (rs != null) {
                rs.close();
            }
        }
        catch (Exception e) {}

        try {con.close();}
        catch (Exception e) {}

        return auto;
    }
}