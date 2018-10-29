package com.kqmh.app.kqmh.Models;

public class ViewFiles {

    private int icon;
    private String title;
    private String counter;

    private boolean isGroupHeader = false;

    public ViewFiles(String title) {
        this(-1, title, null);
        isGroupHeader = true;
    }

    public ViewFiles(int icon, String title, String counter) {
        super();
        this.icon = icon;
        this.title = title;
        this.counter = counter;
    }


    public boolean isGroupHeader() {
        return isGroupHeader;
    }

    public int getIcon() {
        return icon;
    }

    public String getTitle() {
        return title;
    }

    public String getCounter() {
        return counter;
    }
}
