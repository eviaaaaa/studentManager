package com.artisan.model;

public class Department {
    private String did; // 院系ID
    private String dname; // 院系名称

    public Department() {
    }

    public Department(String did, String dname) {
        this.did = did;
        this.dname = dname;
    }

    // Getters and Setters
    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    @Override
    public String toString() {
        return "Department{" +
                "did='" + did + '\'' +
                ", dname='" + dname + '\'' +
                '}';
    }
}
