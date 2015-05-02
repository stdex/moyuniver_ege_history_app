package com.example.shine.testdata.models;

public class Result {

    public String qname;
    public String answer;
    public String correct;
    public String canswer;
    public String cdesc;

    public Result(String qname, String answer, String correct, String canswer, String cdesc) {
        this.qname = qname;
        this.answer = answer;
        this.correct = correct;
        this.canswer = canswer;
        this.cdesc = cdesc;
    }

    public String getQname() {
        return qname;
    }

    public void setQname(String qname) {
        this.qname = qname;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public String getCanswer() {
        return canswer;
    }

    public void setCanswer(String canswer) {
        this.canswer = canswer;
    }

    public String getCdesc() {
        return cdesc;
    }

    public void setCdesc(String cdesc) {
        this.cdesc = cdesc;
    }

    @Override
    public String toString() {
        String appendCans = "";
        if (correct.equals("0")) {
            appendCans =  "\n"+"Правильный ответ: " + canswer;
        }

        String appendCorrect = "";
        if (correct.equals("1")) {
            appendCorrect =  "Да";
        }
        else {
            appendCorrect =  "Нет";
        }
        return "Вопрос: " + qname + "\n" + "Ваш ответ: " + answer + "\n" + "Правильно?: " + appendCorrect + appendCans;
    }
}
