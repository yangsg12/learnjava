package book_manage.vo;

/**
 * Created by Yang on 2015/2/4.
 */
public class SaleRecord extends ValueObject {
    private String RECORD_DATE;

    public String getRECORD_DATE() {
        return RECORD_DATE;
    }

    public void setRECORD_DATE(String RECORD_DATE) {
        this.RECORD_DATE = RECORD_DATE;
    }
}
