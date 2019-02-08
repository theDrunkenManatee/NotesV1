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
        } else {
            System.out.println("ERROR in getNote("+noteName+"), matching note not found");
        }
        return null;
    }

    public boolean addNote(Note noteToAdd) {
        System.out.println("addNote("+noteToAdd+")");
        boolean matchFound = false;
        for(Note note: noteArray) {
            if(note.getName().equals(noteToAdd.getName())) {
                matchFound = true;
            }
        }
        if(!matchFound) {
            System.out.println("no match, returning "+noteToAdd);
            return noteArray.add(noteToAdd);
        } else {
            return false;
        }
    }

    public String toString() {
        String out = folderName;
        for(Note note: noteArray) {
            out = out +"\n-" + note.getName();
        }
        return out;
    }

    //Getter methods
    public String getFolderName() {
        return folderName;
    }
    public ArrayList<Note> getNoteArray() {
        return noteArray;
    }
}
