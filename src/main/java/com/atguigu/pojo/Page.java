package com.atguigu.pojo;

import java.util.List;

/**
 * @author howardy
 * @date 2021/12/27 - 19:49
 */
public class Page<T> {
    public static final int PAGE_SIZE = 4;

    private int pageNum;
    private int pageTotalNum;
    private int pageSize = PAGE_SIZE;
    private int itemsTotalNum;
    private List<T> items;
    private String url;

    public Page(int pageNum, int pageTotalNum, int pageSize, int itemsTotalNum, List<T> items) {
        this.pageNum = pageNum;
        this.pageTotalNum = pageTotalNum;
        this.pageSize = pageSize;
        this.itemsTotalNum = itemsTotalNum;
        this.items = items;
    }

    public Page() {
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        if(pageNum < 1){
            this.pageNum = 1;
        }else if(pageNum > this.pageTotalNum){
            this.pageNum = this.pageTotalNum;
        }else{
            this.pageNum = pageNum;
        }
    }

    public int getPageTotalNum() {
        return pageTotalNum;
    }

    public void setPageTotalNum(int pageTotalNum) {
        this.pageTotalNum = pageTotalNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if(pageSize > 0)
            this.pageSize = pageSize;
    }

    public int getItemsTotalNum() {
        return itemsTotalNum;
    }

    public void setItemsTotalNum(int itemsTotalNum) {
        this.itemsTotalNum = itemsTotalNum;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNum=" + pageNum +
                ", pageTotalNum=" + pageTotalNum +
                ", pageSize=" + pageSize +
                ", itemsTotalNum=" + itemsTotalNum +
                ", items=" + items +
                ", baseUrl='" + url + '\'' +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
