package com.example.notesapp;

import java.util.ArrayList;

public class Folder {
    private ArrayList<Note> noteArray;
    private String folderName;

    public Folder(String name) {
        folderName=name;
        noteArray=new ArrayList<Note>();
    }

    public Note getNote(String noteName) {
        System.out.println("getNote("+noteName+")");
        boolean matchFound = false;
        Note foundNote = null;
        for(Note note: noteArray) {
            if(note.getName().equals(noteName)) {
                matchFound = true;
                foundNote=note;
            }
        }
        if(matchFound) {
            return foundNote;
        }
        return null;
    }

    public boolean addNote(Note noteToAdd) {
        boolean matchFound = false;
        for(Note note: noteArray) {
            if(note.getName().equals(noteToAdd.getName())) {
                matchFound = true;
            }
        }
        if(!matchFound) {
            return noteArray.add(noteToAdd);
        } else {
            return false;
        }
    }

    public void setFolderName(String newName) {
        folderName = newName;
    }

    @Override
    public String toString() {
        String out = folderName;
        for(Note note: noteArray) {
            out = out +"\n-" + note.getName();
        }
        return out;
    }
    @Override
    public int hashCode() {
        return toString().hashCode();
    }
    @Override
    public boolean equals(Object other) {
        return this.toString().equals(other.toString());
    }

    //Getter methods
    public String getFolderName() {
        return folderName;
    }
    public ArrayList<Note> getNoteArray() {
        return noteArray;
    }
}
