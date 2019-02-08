package com.example.notesapp;

public class Note {
    private String name;
    private String content;

    public Note(String noteName) {
        name=noteName;
    }

    public void setName(String nameIn) {
        name = nameIn;
    }
    public void setContent(String contentIn) {
        content =  contentIn;
    }

    public String getName() {
        return name;
    }
    public String getContent() {
        return content;
    }

}
