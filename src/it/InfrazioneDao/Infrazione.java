package it.InfrazioneDao;

import java.sql.Date;

public class Infrazione {
    private int id;
    private Date datan;
    private String tipo;
    private double importo;

    private String targa_auto;

    /**Costruttore*/
    public Infrazione(){

    }



    /**Getter and Setter*/
    public String getTarga_auto() {
        return targa_auto;
    }

    public void setTarga_auto(String targa_auto) {
        this.targa_auto = targa_auto;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDatan() {
        return datan;
    }

    public void setDatan(Date datan) {
        this.datan = datan;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getImporto() {
        return importo;
    }

    public void setImporto(double importo) {
        this.importo = importo;
    }
}
