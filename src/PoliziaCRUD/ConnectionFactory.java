package PoliziaCRUD;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static final String URL="jdbc:postgresql://localhost:5433/multedb";
    public static final String username="postgres";
    public static final String password="Da74bB45s3";

    public static final Logger logger= LoggerFactory.getLogger(ConnectionFactory.class);

    public static Connection getConnection(){
        Connection conn=null;
        try {
            conn= DriverManager.getConnection(URL,username,password);
            logger.info("Connessione effettuata");
        }catch (SQLException e){
            logger.error("Connessione non effettuata");
            e.printStackTrace();
        }
        return conn;
    }

}