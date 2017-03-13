package LifeModeling.x.gui;

import javax.swing.*;

/**
 * Created by Денис on 02.03.2017.
 */
public class MainWindow extends JFrame {

    public MainWindow() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        setBounds(50,50,700,700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(new Map());
        setVisible(true);
    }

}
