package com.qijy.reflects.bean;

/**
 * @author qijy
 * @version 1.0
 * @description: TODO
 * @date 2023/3/24 14:25
 */
public class BBean {
    private int totalPages;
    private long total;
    private int pageNumber;
    private int pageSize;

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "BBean{" +
                "totalPages=" + totalPages +
                ", total=" + total +
                ", pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                '}';
    }
}
