package ru.vsu.sps;


import ru.vsu.sps.gui.MainFrame;

import java.util.Locale;

// 14
public class Task13 {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);

        java.awt.EventQueue.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
