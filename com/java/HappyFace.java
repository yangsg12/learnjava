package com.java;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Yang on 2015/2/3.
 */
public class HappyFace extends JApplet {
    @Override
    public void paint(Graphics g) {
        g.drawOval(100,50,200,200);
        g.fillOval(155, 100, 10, 20);
        g.fillOval(230,100,10,20);
        g.drawArc(150,160,100,50,180,180);
    }
}
