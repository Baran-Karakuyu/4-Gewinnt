/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.Gewinnt4;

import java.util.Observable;

/**
 *
 * @author Baran
 */
public class Model extends Observable {

    private Benutzer benutzer;
    private int position;
    private String[][] spielbrett = new String[6][7];

    public Benutzer getBenutzer() {
        return benutzer;
    }

    public String[][] getSpielbrett() {
        return spielbrett;
    }

    public int getPosition() {
        return position;
    }

    public void setBenutzer(Benutzer benutzer) {
        this.benutzer = benutzer;
    }

    public void setName(String name) {
        benutzer.setName(name);
    }

    public void setZeichen(String zeichen) {
        benutzer.setZeichen(zeichen);
    }

    public void setSpielbrett(String[][] spielbrett) {
        this.spielbrett = spielbrett;
        notifyObservers();
        setChanged();
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
