package jp.hf.commons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Page implements Serializable {
    private static final long serialVersionUID = 1L;

    // データの総件数
    private int count;
    // 現在のページ
    private int page = 0;
    // 1ページの表示件数
    private int rowNum = 0;
    // 表示するページ番号
    private List<PageBean> allPages;
    private String path;

    public Page() {
        super();
    }

    public Page(int rowNum) {
        super();
        this.rowNum = rowNum;
    }

    public Page(int page, int rowNum, String path) {
        super();
        this.page = page;
        this.rowNum = rowNum;
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getrowNum() {
        return rowNum;
    }

    public void setrowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getFrom_rowNum() {
        return (page * rowNum) + 1;
    }

    public int getTo_rowNum() {
        int c = rowNum * (page + 1);
        if (count < c) {
            return count;
        }
        return c;
    }

    public boolean isFirstPage() {
        if (rowNum == -1) {
            return true;
        }
        if (page == 0) {
            return true;
        }
        return false;
    }

    public boolean isLastPage() {
        if (rowNum == -1) {
            return true;
        }
        if ((page + 1) * rowNum < count) {
            return false;
        }
        return true;
    }

    public int getNextPage() {
        return page + 1;
    }

    public int getPrevPage() {
        return page - 1;
    }

    public int getCurrentPage() {
        return page + 1;
    }

    public String getCurrentPageS() {
        return String.valueOf(page + 1);
    }

    public int getMaxPage() {
        if (rowNum == -1) {
            return 1;
        }
        int num = count / rowNum;
        if (count % rowNum != 0) {
            num++;
        }
        return num;
    }

    public List<PageBean> getAllPages() {
        return allPages;
    }

    public void setAllPages(List<PageBean> allPages) {
        this.allPages = allPages;
    }

    /**
     * 10件分のラベルを作成する
     */
    public void buildPageingValue() {
        allPages = new ArrayList<PageBean>();
        // 最大ページを計算
        int tpg = getMaxPage();
        int startPage = getPage() - 5;
        int endPage = getPage() + 5;

        // ページ指定が４までの場合、もしくは最大ページが10以下の場合
        if (getPage() < 5 || tpg <= 10) {
            startPage = 0;
            endPage = 10;
        }

        // 最後のページ番号が最大ページよりも大きくなってしまった場合
        if (endPage > tpg) {
            endPage = tpg;

            // なおかつ、最大ページが10以上の場合は最大ページから前の10件を表示
            if (tpg >= 10) {
                startPage = tpg - 10;
            }
        }

        for (int i = startPage; i < endPage; i++) {
            PageBean bean = new PageBean();
            bean.setPageNumber("" + i);
            bean.setPageName("" + (i + 1));
            allPages.add(bean);
        }
    }
}
