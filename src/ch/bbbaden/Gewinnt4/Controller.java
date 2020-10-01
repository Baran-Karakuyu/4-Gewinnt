/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.Gewinnt4;

import java.awt.Robot;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author Baran
 */
public class Controller {

    private Benutzer s1;
    private Benutzer s2;
    private Benutzer com;
    private String name;
    private int auswahl;
    private final String[][] spielbrett = new String[6][7];
    private int position;
    private boolean gewinnUeberpruefer = false;
    private final Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public void initialisiere() {
        initialisiereSpielerEins();
        auswahl();
        spielfeldAufbauen();
        spielerZuege();
    }

    private void initialisiereSpielerEins() {
        boolean eingabe = false;
        //Fehlerüberprüfung vom Namen
        do {
            name = JOptionPane.showInputDialog(null, "Spieler 1: Gib deinen Namen ein:", "Spielername", JOptionPane.INFORMATION_MESSAGE);
            if (name == null) { // Cancel
                System.exit(0);
            }

            if (name.isEmpty() || name.matches(" ")) { //Wenn Eingabefeld leer
                JOptionPane.showMessageDialog(null, "Bitte gib etwas ein!", "Fehler", JOptionPane.ERROR_MESSAGE);
            } else {
                eingabe = true;
            }
        } while (eingabe != true);

        model.setBenutzer(new Spieler());
        model.setName(name);
        model.setZeichen("X");
        s1 = model.getBenutzer();
    }

    private void auswahl() {
        final Object[] options = {"Computer", "Spieler"};

        final int opt = JOptionPane.showOptionDialog(null,
                "Gegen wen willst du spielen?",
                "Gegnerauswahl",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);

        if (opt == -1) { // Cancel
            System.exit(0);
        }
        if (opt == 1) {
            auswahl = opt;
            initialisiereSpielerZwei();
        } else {
            auswahl = opt;
            
            model.setBenutzer(new Computer());
            model.setName("Bot Bob");
            model.setZeichen("*");
            com = model.getBenutzer();
        }
    }

    private void initialisiereSpielerZwei() {
        boolean eingabe = false;
        //Fehlerüberprüfung vom Namen
        do {
            name = JOptionPane.showInputDialog(null, "Spieler 2: Gib deinen Namen ein:", "Spielername", JOptionPane.INFORMATION_MESSAGE);
            if (name == null) { // Cancel
                System.exit(0);
            }

            if (name.isEmpty() || name.matches(" ")) { //Wenn Eingabefeld leer
                JOptionPane.showMessageDialog(null, "Bitte gib etwas ein!", "Fehler", JOptionPane.ERROR_MESSAGE);
            } else {
                eingabe = true;
            }
        } while (eingabe != true);

        model.setBenutzer(new Spieler());
        model.setName(name);
        model.setZeichen("*");
        s2 = model.getBenutzer();
    }

    private void spielfeldAufbauen() {
        //int x stellt die X-Achse dar
        //int y stellt die Y-Achse dar

        System.out.println("   0 1 2 3 4 5 6");
        for (int y = 0; y < 6; y++) {
            System.out.print(y + 1 + "  ");
            for (int x = 0; x < 7; x++) {
                spielbrett[y][x] = "o";
                System.out.print(spielbrett[y][x] + " ");
            }
            System.out.println("");
        }

        model.setSpielbrett(spielbrett);
    }

    private void spielerZuege() {
        do {
            //Spielzug Spieler1
            //Überprüfung, ob Spalte voll ist
            do {
                position = s1.SpielsteineSetzen();
                if (!(spielbrett[0][position].equals("o"))) {
                    JOptionPane.showMessageDialog(null, "Die Spalte ist voll!", "Warnung!", JOptionPane.WARNING_MESSAGE);
                } else {
                    break;
                }
            } while (true);

            loescheKonsole();

            model.setBenutzer(s1);
            model.setPosition(position);
            model.setSpielbrett(spielbrett);

            gewinnUeberpruefer = gewinn();

            //Gewinnüberprüfung
            if (gewinnUeberpruefer == true) {
                break;
            }

            //Wenn gegen "Spieler" ausgewählt wurde
            if (auswahl == 1) {
                //Überprüfung, ob Spalte voll ist
                do {
                    position = s2.SpielsteineSetzen();
                    if (!(spielbrett[0][position].equals("o"))) {
                        JOptionPane.showMessageDialog(null, "Die Spalte ist voll!", "Warnung!", JOptionPane.WARNING_MESSAGE);
                    } else {
                        break;
                    }
                } while (true);

                loescheKonsole();

                model.setBenutzer(s2);
                model.setPosition(position);
                model.setSpielbrett(spielbrett);

                gewinnUeberpruefer = gewinn();

            } //Wenn gegen "Computer" ausgewählt wurde
            else {
                //Überprüfung, ob Spalte voll ist
                do {
                    position = com.SpielsteineSetzen();
                    if (!(spielbrett[0][position].equals("o"))) {
                        JOptionPane.showMessageDialog(null, "Die Spalte ist voll!", "Warnung!", JOptionPane.WARNING_MESSAGE);
                    } else {
                        break;
                    }
                } while (true);

                loescheKonsole();

                model.setBenutzer(com);
                model.setPosition(position);
                model.setSpielbrett(spielbrett);

                gewinnUeberpruefer = gewinn();
            }
        } while (gewinnUeberpruefer != true);
    }

    private void loescheKonsole() {
        //Löscht Konsolenausgabe
        try {
            Robot pressbot = new Robot();
            pressbot.keyPress(17);      // Holds CTRL key
            Thread.sleep(100);          // wait for 100ms
            pressbot.keyPress(76);      // Holds L key
            Thread.sleep(100);          // wait for 100ms
            pressbot.keyRelease(76);    // Releases L key
            pressbot.keyRelease(17);    // Releases CTRL key
        } catch (Exception e) {
        }
    }

    private boolean gewinn() {
        String benutzerZeichen = model.getBenutzer().getZeichen();

        //int x stellt bei allen die X-Achse dar
        //int y stellt bei allen die Y-Achse dar
        
        //Überprüfung rechts Diagonal
        for (int y = 5; y >= 3; y--) {
            for (int x = 0; x < 4; x++) {
                if (spielbrett[y][x].equals(benutzerZeichen) && spielbrett[y - 1][x + 1].equals(benutzerZeichen) && spielbrett[y - 2][x + 2].equals(benutzerZeichen) && spielbrett[y - 3][x + 3].equals(benutzerZeichen)) {
                    System.out.println(model.getBenutzer().getName() + " hat gewonnen!");
                    JOptionPane.showMessageDialog(null, model.getBenutzer().getName() + " hat gewonnen!", "Gewonnen!", JOptionPane.WARNING_MESSAGE);
                    return true;
                }
            }
        }

        //Überprüfung links Diagonal
        for (int y = 5; y >= 3; y--) {
            for (int x = 0; x < 4; x++) {
                if (spielbrett[y - 3][x].equals(benutzerZeichen) && spielbrett[y - 2][x + 1].equals(benutzerZeichen) && spielbrett[y - 1][x + 2].equals(benutzerZeichen) && spielbrett[y][x + 3].equals(benutzerZeichen)) {
                    System.out.println(model.getBenutzer().getName() + " hat gewonnen!");
                    JOptionPane.showMessageDialog(null, model.getBenutzer().getName() + " hat gewonnen!", "Gewonnen!", JOptionPane.WARNING_MESSAGE);
                    return true;
                }
            }
        }

        //Überprüfung Horizontal
        for (int y = 5; y >= 0; y--) {
            for (int x = 0; x < 4; x++) {
                if ((spielbrett[y][x].equals(benutzerZeichen) && spielbrett[y][x + 1].equals(benutzerZeichen) && spielbrett[y][x + 2].equals(benutzerZeichen) && spielbrett[y][x + 3].equals(benutzerZeichen))) {
                    System.out.println(model.getBenutzer().getName() + " hat gewonnen!");
                    JOptionPane.showMessageDialog(null, model.getBenutzer().getName() + " hat gewonnen!", "Gewonnen!", JOptionPane.WARNING_MESSAGE);
                    return true;
                }
            }
        }

        //Überprüfung Vertikal
        for (int y = 5; y >= 3; y--) {
            for (int x = 0; x < 7; x++) {
                if ((spielbrett[y][x].equals(benutzerZeichen) && spielbrett[y - 1][x].equals(benutzerZeichen) && spielbrett[y - 2][x].equals(benutzerZeichen) && spielbrett[y - 3][x].equals(benutzerZeichen))) {
                    System.out.println(model.getBenutzer().getName() + " hat gewonnen!");
                    JOptionPane.showMessageDialog(null, model.getBenutzer().getName() + " hat gewonnen!", "Gewonnen!", JOptionPane.WARNING_MESSAGE);
                    return true;
                }
            }
        }

        //Überprüft, ob alle Felder besetzt sind
        for (int y = 0; y < 1; y++) {
            for (int x = 0; x < 1; x++) {
                if (!(spielbrett[y][x].equals("o")) && !(spielbrett[y][x + 1].equals("o")) && !(spielbrett[y][x + 2].equals("o")) && !(spielbrett[y][x + 3].equals("o")) && !(spielbrett[y][x + 4].equals("o")) && !(spielbrett[y][x + 5].equals("o")) && !(spielbrett[y][x + 6].equals("o"))) {
                    System.out.println("Keiner hat gewonnen!");
                    JOptionPane.showMessageDialog(null, "Keiner hat gewonnen!", "Unentschieden!", JOptionPane.WARNING_MESSAGE);
                    return true;
                }
            }
        }
        return false;
    }
}
