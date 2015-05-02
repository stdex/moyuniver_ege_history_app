package com.example.shine.testdata.models;

public class Answer {

    public String aid;
    public String qtype;
    public String atxt;
    public String empty;
    public String correct;

    public Answer(String aid, String qtype, String atxt, String empty, String correct) {
        this.aid = aid;
        this.qtype = qtype;
        this.atxt = atxt;
        this.empty = empty;
        this.correct = correct;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getQtype() {
        return qtype;
    }

    public void setQtype(String qtype) {
        this.qtype = qtype;
    }

    public String getAtxt() {
        return atxt;
    }

    public void setAtxt(String atxt) {
        this.atxt = atxt;
    }

    public String getEmpty() {
        return empty;
    }

    public void setEmpty(String empty) {
        this.empty = empty;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Answer)) return false;

        Answer answer = (Answer) o;

        if (aid != null ? !aid.equals(answer.aid) : answer.aid != null) return false;
        if (correct != null ? !correct.equals(answer.correct) : answer.correct != null)
            return false;
        if (empty != null ? !empty.equals(answer.empty) : answer.empty != null) return false;
        if (atxt != null ? !atxt.equals(answer.atxt) : answer.atxt != null) return false;
        if (qtype != null ? !qtype.equals(answer.qtype) : answer.qtype != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = aid != null ? aid.hashCode() : 0;
        result = 31 * result + (qtype != null ? qtype.hashCode() : 0);
        result = 31 * result + (atxt != null ? atxt.hashCode() : 0);
        result = 31 * result + (empty != null ? empty.hashCode() : 0);
        result = 31 * result + (correct != null ? correct.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "aid='" + aid + '\'' +
                ", qtype='" + qtype + '\'' +
                ", atxt='" + atxt + '\'' +
                ", empty='" + empty + '\'' +
                ", correct='" + correct + '\'' +
                '}';
    }
}