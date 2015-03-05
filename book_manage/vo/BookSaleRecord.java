package book_manage.vo;

/**
 * Created by Yang on 2015/2/4.
 */
public class BookSaleRecord extends ValueObject {
    private String BOOK_ID_FK;
    private String T_IN_RECORD_ID_FK;
    private String TRADE_SUM;

    public String getBOOK_ID_FK() {
        return BOOK_ID_FK;
    }

    public void setBOOK_ID_FK(String BOOK_ID_FK) {
        this.BOOK_ID_FK = BOOK_ID_FK;
    }

    public String getT_IN_RECORD_ID_FK() {
        return T_IN_RECORD_ID_FK;
    }

    public void setT_IN_RECORD_ID_FK(String t_IN_RECORD_ID_FK) {
        T_IN_RECORD_ID_FK = t_IN_RECORD_ID_FK;
    }

    public String getTRADE_SUM() {
        return TRADE_SUM;
    }

    public void setTRADE_SUM(String TRADE_SUM) {
        this.TRADE_SUM = TRADE_SUM;
    }
}
