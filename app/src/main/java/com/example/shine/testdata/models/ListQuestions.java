package com.example.shine.testdata.models;

import java.util.LinkedHashMap;
import java.util.Map;

public class ListQuestions {
    public String tid;
    public Map<String,Question> mQuestions = new LinkedHashMap<String,Question>();

    public ListQuestions(String tid, Map<String, Question> mQuestions) {
        this.tid = tid;
        this.mQuestions = mQuestions;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public Map<String, Question> getmQuestions() {
        return mQuestions;
    }

    public void setmQuestions(Map<String, Question> mQuestions) {
        this.mQuestions = mQuestions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListQuestions)) return false;

        ListQuestions that = (ListQuestions) o;

        if (mQuestions != null ? !mQuestions.equals(that.mQuestions) : that.mQuestions != null)
            return false;
        if (tid != null ? !tid.equals(that.tid) : that.tid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tid != null ? tid.hashCode() : 0;
        result = 31 * result + (mQuestions != null ? mQuestions.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ListQuestions{" +
                "tid='" + tid + '\'' +
                ", mQuestions=" + mQuestions +
                '}';
    }
}