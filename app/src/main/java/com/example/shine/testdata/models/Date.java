package com.example.shine.testdata.models;

public class Date {
    public String century;
    public String year;
    public String event;

    public Date(String century, String year, String event) {
        this.century = century;
        this.year = year;
        this.event = event;
    }

    public String getCentury() {
        return century;
    }

    public void setCentury(String century) {
        this.century = century;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Date)) return false;

        Date date = (Date) o;

        if (century != null ? !century.equals(date.century) : date.century != null) return false;
        if (event != null ? !event.equals(date.event) : date.event != null) return false;
        if (year != null ? !year.equals(date.year) : date.year != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = century != null ? century.hashCode() : 0;
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (event != null ? event.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return year + " - " + event;
    }
}
