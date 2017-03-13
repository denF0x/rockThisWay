package LifeModeling.runner;

import LifeModeling.x.gui.MainWindow;

/**
 * Created by Денис on 02.03.2017.
 */
public class Runner {
    public static void main(String[] args) {
        try {
            MainWindow mainWindow = new MainWindow();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
