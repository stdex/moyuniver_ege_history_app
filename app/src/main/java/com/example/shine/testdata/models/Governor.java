package com.example.shine.testdata.models;

public class Governor {
    public String tag;
    public String governor;
    public String description;

    public Governor(String tag, String governor, String description) {
        this.tag = tag;
        this.governor = governor;
        this.description = description;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getGovernor() {
        return governor;
    }

    public void setGovernor(String governor) {
        this.governor = governor;
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
        if (!(o instanceof Governor)) return false;

        Governor governor1 = (Governor) o;

        if (description != null ? !description.equals(governor1.description) : governor1.description != null)
            return false;
        if (governor != null ? !governor.equals(governor1.governor) : governor1.governor != null)
            return false;
        if (tag != null ? !tag.equals(governor1.tag) : governor1.tag != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tag != null ? tag.hashCode() : 0;
        result = 31 * result + (governor != null ? governor.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Governor{" +
                "tag='" + tag + '\'' +
                ", governor='" + governor + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
