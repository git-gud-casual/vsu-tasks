package ru.vsu.sps;


import ru.vsu.sps.gui.MainFrame;

import java.util.Locale;

// 16
public class Task10 {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
}
