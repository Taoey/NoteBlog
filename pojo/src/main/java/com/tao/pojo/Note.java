package com.tao.pojo;

import java.util.Date;

public class Note {
    private Integer id;

    private String guid;

    private String title;

    private Date time;

    private Double isupdated;

    private Integer ishave;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid == null ? null : guid.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Double getIsupdated() {
        return isupdated;
    }

    public void setIsupdated(Double isupdated) {
        this.isupdated = isupdated;
    }

    public Integer getIshave() {
        return ishave;
    }

    public void setIshave(Integer ishave) {
        this.ishave = ishave;
    }
}