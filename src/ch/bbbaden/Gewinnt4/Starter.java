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
public class Starter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Model m = new Model();
        View v = new View();
        
        m.addObserver(v);
        
        Controller c = new Controller(m);
        c.initialisiere();
    }
    
}
