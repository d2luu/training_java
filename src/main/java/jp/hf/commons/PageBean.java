package jp.hf.commons;

import java.io.Serializable;

public class PageBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String pageNumber;
    private String pageName;
    private String value;

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
