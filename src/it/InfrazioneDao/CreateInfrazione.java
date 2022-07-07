package it.InfrazioneDao;

import PoliziaCRUD.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateInfrazione {
    public void createTable(){
        Connection conn= ConnectionFactory.getConnection();
        Statement statement;

        try{
            statement= conn.createStatement();
            statement.executeUpdate("Create Table infrazione (id smallint primary key,datan date not null,tipo varchar(30)not null,importo decimal(13,2)not null,targa_auto varchar(20)," +
                    "foreign key(targa_auto)references auto(targa))");
            System.out.println("Creazione tabella effettuata");
        }catch(SQLException e){
            System.out.println("Creazione tabella non riuscita");
            e.printStackTrace();
        }
    }
}
