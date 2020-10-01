/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.Gewinnt4;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Baran
 */
public class View implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        String[][] spielbrett;

        Model m = (Model) o;

        spielbrett = m.getSpielbrett();

        for (int i = 5; i >= 0; i--) {
            if (spielbrett[i][m.getPosition()].equals("o")) {                   //Schaut, ob es einen freien Platz hat
                spielbrett[i][m.getPosition()] = m.getBenutzer().getZeichen();  //Zeichen des aktuellen Spielers wird eingesetzt
                break;
            }
        }

        //Spielbrett mit änderungen wird ausgegeben
        System.out.println("");
        System.out.println("   0 1 2 3 4 5 6");
        for (int i = 0; i < 6; i++) {
            System.out.print(i + "  ");
            for (int j = 0; j < 7; j++) {
                System.out.print(spielbrett[i][j] + " ");
            }
            System.out.println("");
        }
    }
}
