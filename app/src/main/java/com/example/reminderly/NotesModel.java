package com.example.reminderly;

public class NotesModel {
    private String id;
    private String title;
    private String description;
    private String uid;

    public NotesModel() {
    }

    public NotesModel(String id, String title, String description, String uid) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {

        this.uid = uid;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {

        this.id = id;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getDesc() {

        return description;
    }

    public void setDesc(String description) {

        this.description = description;
    }
}
