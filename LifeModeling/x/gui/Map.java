package LifeModeling.x.gui;

import LifeModeling.x.ucf.UCFCoder;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Random;

/**
 * Created by Денис on 02.03.2017.
 */
public class Map extends JTable {

    private Random random = new Random();

    private final int mapSize = 129;
    private final int cellSize = 5;

    public Map() {
        setModel(new DefaultTableModel(this.mapSize, this.mapSize));
        setRowHeight(this.cellSize);
        for (int i = 0; i < this.mapSize; i++) {
            this.getColumnModel().getColumn(i).setMinWidth(this.cellSize);
            this.getColumnModel().getColumn(i).setMaxWidth(this.cellSize);
        }
        for (int x = 0; x < this.mapSize; x++) {
            for (int y = 0; y < this.mapSize; y++) {
                setValueAt(0x0000, y, x);
            }
        }
        generateLandscape();
        setVisible(true);
    }

    private final void generateLandscape() {

        setValueAt(UCFCoder.LANDSCAPE_MOUNTAIN_LOW, 0, 0);
        setValueAt(UCFCoder.LANDSCAPE_MOUNTAIN_LOW, 0, this.mapSize - 1);
        setValueAt(UCFCoder.LANDSCAPE_MOUNTAIN_LOW, this.mapSize - 1, 0);
        setValueAt(UCFCoder.LANDSCAPE_MOUNTAIN_LOW, this.mapSize - 1, this.mapSize - 1);

        float landscapeShift = 15.0f;

        for (int bigStep = this.mapSize - 1; bigStep >= 2; bigStep /= 2, landscapeShift /= 2.0f) {
            int smallStep = bigStep / 2;
            // Diamond step - build diamonds
            for (int x = smallStep; x < this.mapSize; x += bigStep) {
                for (int y = smallStep; y < this.mapSize; y += bigStep) {
                    int topLeftValue = (int)getValueAt(y - smallStep, x - smallStep);
                    int topRightValue = (int)getValueAt(y - smallStep, x + smallStep);
                    int bottomLeftValue = (int)getValueAt(y + smallStep, x - smallStep);
                    int bottomRightValue = (int)getValueAt(y + smallStep, x + smallStep);
                    float avg =  (topLeftValue + topRightValue + bottomLeftValue + bottomRightValue) / 4;
                    int centerValue = getValueInRange(avg + this.random.nextInt(3) * landscapeShift - landscapeShift, 1, 15);
                    setValueAt(centerValue, y, x);
                }
            }
            // Square step - build squares
            for (int x = 0; x < this.mapSize; x += smallStep) {
                for (int y = (x + smallStep) % bigStep; y < this.mapSize; y += bigStep) {
                    int topValue = (int)getValueAt((y - smallStep + this.mapSize - 1) % (this.mapSize - 1), x);
                    int leftValue = (int)getValueAt(y, (x - smallStep + this.mapSize - 1) % (this.mapSize - 1));
                    int rightValue = (int)getValueAt(y, (x + smallStep) % (this.mapSize - 1));
                    int bottomValue = (int)getValueAt((y + smallStep) % (this.mapSize - 1), x);
                    float avg =  (topValue + leftValue + rightValue + bottomValue) / 4;
                    int centerValue = getValueInRange(avg + this.random.nextInt(3) * landscapeShift - landscapeShift, 1, 15);
                    setValueAt(centerValue, y, x);
                }
            }
        }
    }

    private int getValueInRange(float value, int min, int max) {
        return (int)Math.max(Math.min(value, max), min);
    }
}
