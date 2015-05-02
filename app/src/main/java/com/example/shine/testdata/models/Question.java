package com.example.shine.testdata.models;

import java.util.Collection;


import java.util.Collection;

public class Question {

    public String qid;
    public String qtype;
    public String qtxt;
    public String tsid;
    public Collection<Answer> mAnswers;

    public Question(String qid, String qtype, String qtxt, String tsid, Collection<Answer> mAnswers) {
        this.qid = qid;
        this.qtype = qtype;
        this.qtxt = qtxt;
        this.tsid = tsid;
        this.mAnswers = mAnswers;
    }

    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid;
    }

    public String getQtype() {
        return qtype;
    }

    public void setQtype(String qtype) {
        this.qtype = qtype;
    }

    public String getQtxt() {
        return qtxt;
    }

    public void setQtxt(String qtxt) {
        this.qtxt = qtxt;
    }

    public String getTsid() {
        return tsid;
    }

    public void setTsid(String tsid) {
        this.tsid = tsid;
    }

    public Collection<Answer> getmAnswers() {
        return mAnswers;
    }

    public void setmAnswers(Collection<Answer> mAnswers) {
        this.mAnswers = mAnswers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;

        Question question = (Question) o;

        if (mAnswers != null ? !mAnswers.equals(question.mAnswers) : question.mAnswers != null)
            return false;
        if (qid != null ? !qid.equals(question.qid) : question.qid != null) return false;
        if (qtxt != null ? !qtxt.equals(question.qtxt) : question.qtxt != null) return false;
        if (qtype != null ? !qtype.equals(question.qtype) : question.qtype != null) return false;
        if (tsid != null ? !tsid.equals(question.tsid) : question.tsid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = qid != null ? qid.hashCode() : 0;
        result = 31 * result + (qtype != null ? qtype.hashCode() : 0);
        result = 31 * result + (qtxt != null ? qtxt.hashCode() : 0);
        result = 31 * result + (tsid != null ? tsid.hashCode() : 0);
        result = 31 * result + (mAnswers != null ? mAnswers.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Question{" +
                "qid='" + qid + '\'' +
                ", qtype='" + qtype + '\'' +
                ", qtxt='" + qtxt + '\'' +
                ", tsid='" + tsid + '\'' +
                ", mAnswers=" + mAnswers +
                '}';
    }
}