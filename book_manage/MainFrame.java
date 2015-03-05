//package book_manage;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//
///**
// * Created by Yang on 2015/2/4.
// */
//public class MainFrame {
//    private void changePanel(CommonPanel commonPanel) {
//        this.remove(currentPanel);
//        this.add(commonPanel);
//        this.currentPanel = commonPanel;
//        this.repaint();
//        this.setVisible(true);
//        commonPanel.setViewDatas();
//        commonPanel.clear();
//    }
//
//    private Action sale = new AbstractAction("销售管理") {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            changePanel(salePanel);
//
//        }
//    };
//}