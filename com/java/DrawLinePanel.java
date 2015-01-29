package com.java;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Yang on 14-2-3.
 */




public class DrawLinePanel extends JPanel {
    @Override
    public void paint(Graphics g) {
        g.drawLine(70, 50, 180, 50);
        g.drawLine(70, 80, 180, 80);
        g.drawLine(110, 10, 140, 120);
    }
}

