package com.hst.shrp.model.entity;

/**
 * @author dudgns0612@gmail.com
 */
public class EntityCommonCode {
    private String grpCd;
    private String subCd;
    private String subNm;
    private String desc;

    public String getGrpCd() {
        return grpCd;
    }

    public void setGrpCd(String grpCd) {
        this.grpCd = grpCd;
    }

    public String getSubCd() {
        return subCd;
    }

    public void setSubCd(String subCd) {
        this.subCd = subCd;
    }

    public String getSubNm() {
        return subNm;
    }

    public void setSubNm(String subNm) {
        this.subNm = subNm;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "EntityCommonCode{" +
                "grpCd='" + grpCd + '\'' +
                ", subCd='" + subCd + '\'' +
                ", subNm='" + subNm + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
