/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.Gewinnt4;

/**
 *
 * @author Baran
 */
public interface Benutzer {

    public String getName();

    public String getZeichen();

    public void setName(String name);

    public void setZeichen(String zeichen);
    
    public int SpielsteineSetzen();
}
