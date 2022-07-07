package it.AutoDao;

import PoliziaCRUD.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateAuto {
    public void createTable(){
        Connection conn= ConnectionFactory.getConnection();
        Statement statement;

        try{
            statement= conn.createStatement();
            statement.executeUpdate("Create Table auto (targa varchar(12)primary key,modello varchar(40) not null,marca varchar(20) not null)");
            System.out.println("Creazione tabella effettuata");
        }catch(SQLException e){
            System.out.println("Creazione tabella non riuscita");
            e.printStackTrace();
        }
    }
}
