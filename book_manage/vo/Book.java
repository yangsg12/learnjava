package book_manage.vo;

/**
 * Created by Yang on 2015/2/4.
 */
public class Book extends ValueObject {
    private String BOOK_NAME;
    private String BOOK_INTRO;
    private String BOOK_PRICE;
    private String IMAGE_URL;
    private String AUTHOR;
    private String REPERTORY_SIZE;

    public String getBOOK_NAME() {
        return BOOK_NAME;
    }

    public void setBOOK_NAME(String BOOK_NAME) {
        this.BOOK_NAME = BOOK_NAME;
    }

    public String getREPERTORY_SIZE() {
        return REPERTORY_SIZE;
    }

    public void setREPERTORY_SIZE(String REPERTORY_SIZE) {
        this.REPERTORY_SIZE = REPERTORY_SIZE;
    }

    public String getAUTHOR() {
        return AUTHOR;
    }

    public void setAUTHOR(String AUTHOR) {
        this.AUTHOR = AUTHOR;
    }

    public String getBOOK_PRICE() {
        return BOOK_PRICE;
    }

    public void setBOOK_PRICE(String BOOK_PRICE) {
        this.BOOK_PRICE = BOOK_PRICE;
    }

    public String getIMAGE_URL() {
        return IMAGE_URL;
    }

    public void setIMAGE_URL(String IMAGE_URL) {
        this.IMAGE_URL = IMAGE_URL;
    }

    public String getBOOK_INTRO() {
        return BOOK_INTRO;
    }

    public void setBOOK_INTRO(String BOOK_INTRO) {
        this.BOOK_INTRO = BOOK_INTRO;
    }
}
