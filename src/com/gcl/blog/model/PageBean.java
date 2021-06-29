package com.gcl.blog.model;

import java.util.List;

/**
 * 分页展示的实体类
 */
public class PageBean<Blog>{
    //基本属性
    private int currentPageNum;//当前页数，由用户指定
    private int pageSize=4;//每页显示的条数，固定的
    private int totalRecords;//总记录条数，数据库中查出来的
    private int totalPageNum;//总页数，计算出来的
    private List<Blog> list;//已经分好页的结果集
    //构造方法
    public PageBean(){}
    public PageBean(int currentPageNum, int pageSize, int totalRecords, int totalPageNum, List<Blog> list) {
        this.currentPageNum = currentPageNum;
        this.pageSize = pageSize;
        this.totalRecords = totalRecords;
        this.totalPageNum = totalPageNum;
        this.list = list;
    }

    //getter和setter方法

    public int getCurrentPageNum() {
        return currentPageNum;
    }

    public void setCurrentPageNum(int currentPageNum) {
        this.currentPageNum = currentPageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getTotalPageNum() {
        return totalPageNum;
    }

    public void setTotalPageNum(int totalPageNum) {
        this.totalPageNum = totalPageNum;
    }

    public List<Blog> getList() {
        return list;
    }

    public void setList(List<Blog> list) {
        this.list = list;
    }
}
