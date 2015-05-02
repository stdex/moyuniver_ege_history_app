package com.example.shine.testdata.models;

public class MyTest {

    public String eid;
    public String ename;
    public String edesc;
    public String imglnk;
    public String tid;

    public MyTest(String eid, String ename, String edesc, String imglnk, String tid) {
        this.eid = eid;
        this.ename = ename;
        this.edesc = edesc;
        this.imglnk = imglnk;
        this.tid = tid;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getEdesc() {
        return edesc;
    }

    public void setEdesc(String edesc) {
        this.edesc = edesc;
    }

    public String getImglnk() {
        return imglnk;
    }

    public void setImglnk(String imglnk) {
        this.imglnk = imglnk;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    @Override
    public String toString() {
        return ename;
    }
}
