//package book_manage;
//
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.util.Vector;
//
///**
// * Created by Yang on 2015/2/4.
// */
//public class CommonPanel {
//    private Vector<Vector> datas;
//    private JTable table;
//
//    public Vector<Vector> getDatas() {
//        return datas;
//    }
//
//    public void setDatas(Vector<Vector> datas) {
//        this.datas = datas;
//    }
//
//    public JTable getTable() {
//
//        return table;
//    }
//
//    public void setTable(JTable table) {
//        this.table = table;
//    }
//
//    public void initData() {
//        if (this.table == null) {
//            return;
//        }
//        // 数据放到 tableModel 中
//        DefaultTableModel tableModel = (DefaultTableModel)this.table.getModel();
//        tableModel.setDataVector(getDatas(),getColumns());
//
//        setTableFace();
//    }
//
//    public void refreshTable(){
//        initData();
//        getJTable.repaint();
//
//    }
//    // 列表合集
//    public abstract Vector<String> getColumns;
//    // 列表样式
//    public abstract void setTableFace();
//
//    public abstract void setViewDatas();
//
//    public abstract void clear();
//
//    public String getSelectId(JTable table){
//        int row = table.getSelectedRow();
//        int column = table.getColumn("id").getModelIndex();
//        String id = (String)table.getValueAt(row, column);
//        return id;
//    }
//}
