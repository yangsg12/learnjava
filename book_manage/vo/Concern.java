package book_manage.vo;


/**
 * Created by Yang on 2015/2/4.
 * publisher
 */
public class Concern extends ValueObject {
    private String PUB_NAME;
    private String PUB_TEL;
    private String PUB_LINK_MAN;
    private String PUB_INTRO;

    public String getPUB_NAME() {
        return PUB_NAME;
    }

    public void setPUB_NAME(String PUB_NAME) {
        this.PUB_NAME = PUB_NAME;
    }

    public String getPUB_INTRO() {
        return PUB_INTRO;
    }

    public void setPUB_INTRO(String PUB_INTRO) {
        this.PUB_INTRO = PUB_INTRO;
    }

    public String getPUB_LINK_MAN() {
        return PUB_LINK_MAN;
    }

    public void setPUB_LINK_MAN(String PUB_LINK_MAN) {
        this.PUB_LINK_MAN = PUB_LINK_MAN;
    }

    public String getPUB_TEL() {
        return PUB_TEL;
    }

    public void setPUB_TEL(String PUB_TEL) {
        this.PUB_TEL = PUB_TEL;
    }
}
