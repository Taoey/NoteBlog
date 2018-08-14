package com.tao.pojo;

public class Note2tag {
    private Integer id;

    private String noteguid;

    private String tagguid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoteguid() {
        return noteguid;
    }

    public void setNoteguid(String noteguid) {
        this.noteguid = noteguid == null ? null : noteguid.trim();
    }

    public String getTagguid() {
        return tagguid;
    }

    public void setTagguid(String tagguid) {
        this.tagguid = tagguid == null ? null : tagguid.trim();
    }
}