package com.king.GUI;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {
    public static void main(String args[]) throws IOException
    {
        SpashScreen spl = new SpashScreen();
        spl.setVisible(true);
        spl.setLocationRelativeTo(null);
        DictionaryGUI gui = new DictionaryGUI("config.txt");
        gui.setLocationRelativeTo(null);
        try {
            for(int i = 0; i<=100; i++) {
                 Thread.sleep(10);
                 spl.updatePercent(i);
                 if (i == 100) {
                     spl.setVisible(false);
                     gui.setVisible(true);
                 }
            }
           
        } catch (InterruptedException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
