package book_manage.vo;

/**
 * Created by Yang on 2015/2/4.
 */
public class Type extends ValueObject {
    private String TYPE_NAME;
    private String TYPE_INTRO;

    public String getTYPE_NAME() {
        return TYPE_NAME;
    }

    public void setTYPE_NAME(String TYPE_NAME) {
        this.TYPE_NAME = TYPE_NAME;
    }

    public String getTYPE_INTRO() {
        return TYPE_INTRO;
    }

    public void setTYPE_INTRO(String TYPE_INTRO) {
        this.TYPE_INTRO = TYPE_INTRO;
    }
}
