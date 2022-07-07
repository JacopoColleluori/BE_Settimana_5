package PoliziaCRUD;

import it.AutoDao.Auto;
import it.AutoDao.AutoDao;
import it.InfrazioneDao.Infrazione;
import it.InfrazioneDao.InfrazioneDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

public class MainProgetto {
    private static final Logger logger = LoggerFactory.getLogger(MainProgetto.class);

    public static void main(String[] args) {

        /* CreateAuto creaAuto=new CreateAuto();     La creazione della tabella avviene una sola volta
        creaTabella.createTable();*/

         /* CreateInfrazione creaInfrazione  =new CreateInfrazione();      //La creazione della tabella avviene una sola volta
        creaInfrazione.createTable();*/

    Menu();
    }

    public static void Menu() {
        String menu = """
                
                ------------ Benvenuto nell'applicazione della polizia municipale ------------\s
                
                1) Inserire dati automobile

                2) Inserire dati infrazione

                3) Visualizza le auto nel Database

                4) Cercare auto per targa

                5)Visualizza Dati infrazione DB

                6)Elimina infrazione con ID
                                
                10)Uscita dall'applicazione
                
                ------------------------------------------------------------------------------
                """;
        logger.info(menu);
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();

        AutoDao autoDao = new AutoDao();
        InfrazioneDao infraDao=new InfrazioneDao();
        while (x != 10) {

            Auto auto = new Auto();
            switch (x) {
                //IMMISSIONE AUTO
                case 1 -> {
                    logger.info("Inserisca la targa dell'auto: \n");
                    scanner.nextLine();
                    auto.setTarga(scanner.nextLine());
                    logger.info("Inserisca la marca dell'auto: \n");
                    auto.setMarca(scanner.nextLine());
                    logger.info("Inserisca Modello auto: \n");
                    auto.setModello(scanner.nextLine());
                    autoDao.inserisciAuto(auto);
                }
                //IMMISSIONE INFRAZIONE
                case 2 -> {
                    Infrazione infrazione = new Infrazione();
                    logger.info("Inserisca id dell'infrazione : \n");
                    infrazione.setId(scanner.nextInt());
                    logger.info("Inserisca data infrazione nel formato corretto (yyyy-mm-dd): \n");
                    String stringa = scanner.next();
                    Date data = Date.valueOf(stringa);
                    scanner.nextLine();
                    infrazione.setDatan(data);
                    logger.info("Inserisca tipo dell'infrazione : \n");
                    infrazione.setTipo(scanner.nextLine());
                    logger.info("Inserisca importo dell'infrazione : \n");
                    infrazione.setImporto(scanner.nextDouble());
                    scanner.nextLine();
                    logger.info("Inserisca targa dell'auto incriminata : \n");
                    infrazione.setTarga_auto(scanner.nextLine());
                    infraDao.inserisciInfrazione(infrazione);
                }

                //LISTA AUTO DB
                case 3 -> {
                    ArrayList<Auto> autoDB = autoDao.getAllAuto();
                    logger.info("Database Auto: \n");
                    for (Auto automobile : autoDB) {
                        logger.info("\nTarga auto: " + automobile.getTarga() +
                                    "\n Marca auto:" + automobile.getMarca() +
                                    "\n Modello auto: " + automobile.getModello() + "\n");
                    }
                }

                //CERCA AUTO PER TARGA
                case 4 -> {
                    logger.info("Immetta la targa da cercare: ");
                    scanner.nextLine();
                    auto = autoDao.cercaAuto(scanner.nextLine());
                    logger.info("\nTarga: " + auto.getTarga() + " \nModello: " + auto.getModello() + "\n" +
                                "Marca: " + auto.getMarca() + "\n");
                }
                //LISTA INFRAZIONI DB
                case 5 -> {
                    logger.info("Database Infrazioni: \n");
                    infraDao.StampaDatiInfrazioni();
                }
                case 6 -> {
                    logger.info("Inserisca l'id della infrazione da eliminare: ");
                    infraDao.eliminaInfrazione(scanner.nextInt());
                    scanner.nextLine();
                }
                default ->
                        logger.info("L'opzione scelta non e' consentita, immetta una opzione valida tra le seguenti:" + menu);
            }
            logger.info(menu);
            x = scanner.nextInt();
            scanner.nextLine();
        }
    }
}
