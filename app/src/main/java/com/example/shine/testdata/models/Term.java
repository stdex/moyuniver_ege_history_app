package com.example.shine.testdata.models;


public class Term {
    public String tag;
    public String term;
    public String description;

    public Term(String tag, String term, String description) {
        this.tag = tag;
        this.term = term;
        this.description = description;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Term)) return false;

        Term term1 = (Term) o;

        if (description != null ? !description.equals(term1.description) : term1.description != null)
            return false;
        if (tag != null ? !tag.equals(term1.tag) : term1.tag != null) return false;
        if (term != null ? !term.equals(term1.term) : term1.term != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tag != null ? tag.hashCode() : 0;
        result = 31 * result + (term != null ? term.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()  {
        return term + " - " + description;
    }
}
