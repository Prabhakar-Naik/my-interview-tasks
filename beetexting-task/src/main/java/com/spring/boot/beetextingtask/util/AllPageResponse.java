package com.spring.boot.beetextingtask.util;

import lombok.*;

import java.util.List;


public class AllPageResponse {
    private Long totalRecords = 0L;
    private List<?> pageResponse = null;

    public AllPageResponse() {
        super();
    }

    public AllPageResponse(Long totalRecords, List<?> pageResponse) {
        this.totalRecords = totalRecords;
        this.pageResponse = pageResponse;
    }


    public Long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public List<?> getPageResponse() {
        return pageResponse;
    }

    public void setPageResponse(List<?> pageResponse) {
        this.pageResponse = pageResponse;
    }
}