package it.AutoDao;

public class Auto {
    private   String targa;
    private   String modello;
    private   String marca;

 /** Costruttori*/
    public Auto(){

    }
    /** Getter e Setter*/
    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}
