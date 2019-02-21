package br.com.paulosalvatore.pagseguroandroidturmaa;

import androidx.annotation.DrawableRes;

class ProgrammingLanguage {
    @DrawableRes
    private int imageResourceId;
    private String title;
    private int year;
    private String description;

    public ProgrammingLanguage(int imageResourceId, String title, int year, String description) {
        this.imageResourceId = imageResourceId;
        this.title = title;
        this.year = year;
        this.description = description;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
