package com.yangyang.apiplatform.entity;

//serviceprovider实体类
public class Sp {
    String sp_id;
    String sp_name;
    String sp_org_id;
    String sp_description;
    String sp_tel;
    String sp_representative;
    String sp_representative_id;
    String sp_email;
    String sp_password;

    @Override
    public String toString() {
        return "Sp{" +
                "sp_id='" + sp_id + '\'' +
                ", sp_name='" + sp_name + '\'' +
                ", sp_org_id='" + sp_org_id + '\'' +
                ", sp_description='" + sp_description + '\'' +
                ", sp_tel='" + sp_tel + '\'' +
                ", sp_representative='" + sp_representative + '\'' +
                ", sp_representative_id='" + sp_representative_id + '\'' +
                ", sp_email='" + sp_email + '\'' +
                ", sp_password='" + sp_password + '\'' +
                '}';
    }

    public String getSp_id() {
        return sp_id;
    }

    public void setSp_id(String sp_id) {
        this.sp_id = sp_id;
    }

    public String getSp_name() {
        return sp_name;
    }

    public void setSp_name(String sp_name) {
        this.sp_name = sp_name;
    }

    public String getSp_org_id() {
        return sp_org_id;
    }

    public void setSp_org_id(String sp_org_id) {
        this.sp_org_id = sp_org_id;
    }

    public String getSp_description() {
        return sp_description;
    }

    public void setSp_description(String sp_description) {
        this.sp_description = sp_description;
    }

    public String getSp_tel() {
        return sp_tel;
    }

    public void setSp_tel(String sp_tel) {
        this.sp_tel = sp_tel;
    }

    public String getSp_representative() {
        return sp_representative;
    }

    public void setSp_representative(String sp_representative) {
        this.sp_representative = sp_representative;
    }

    public String getSp_representative_id() {
        return sp_representative_id;
    }

    public void setSp_representative_id(String sp_representative_id) {
        this.sp_representative_id = sp_representative_id;
    }

    public String getSp_email() {
        return sp_email;
    }

    public void setSp_email(String sp_email) {
        this.sp_email = sp_email;
    }

    public String getSp_password() {
        return sp_password;
    }

    public void setSp_password(String sp_password) {
        this.sp_password = sp_password;
    }
}

