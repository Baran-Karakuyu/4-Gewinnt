/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.Gewinnt4;

import javax.swing.JOptionPane;

/**
 *
 * @author Baran
 */
public class Spieler implements Benutzer{

    private String name;
    private String zeichen;
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getZeichen() {
        return zeichen;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setZeichen(String zeichen) {
        this.zeichen = zeichen;
    }
    
    @Override
    public int SpielsteineSetzen() {
        boolean ueberpruefer;
        String eingabe;
        int auswahlPosition = 0;

        do {
            try {
                eingabe = JOptionPane.showInputDialog(null, name + " in welcher Kolone möchtest du den Stein setzen?", "Zug", JOptionPane.QUESTION_MESSAGE);

                if (eingabe == null) { // Cancel
                    System.exit(0);
                }

                if (Integer.valueOf(eingabe) > 6 || Integer.valueOf(eingabe) < 0) {
                    JOptionPane.showMessageDialog(null, "Bitte gib eine Zahl zwischen 0 und 6 ein!", "Fehler", JOptionPane.ERROR_MESSAGE);
                    ueberpruefer = false;
                } else {
                    ueberpruefer = true;
                }

                auswahlPosition = Integer.valueOf(eingabe);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Bitte gib eine Zahl ein!", "Fehler", JOptionPane.ERROR_MESSAGE);
                ueberpruefer = false;
            }
        } while (ueberpruefer != true);
        return auswahlPosition;
    }
}
