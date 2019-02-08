package com.example.notesapp;

import java.util.ArrayList;

public class NoteAppHandler {
    private ArrayList<Folder> folders;
    private Folder selectedFolder;
    private Note testNote;
    private Note selectedNote;

    public NoteAppHandler() {
        folders = new ArrayList<Folder>();
        selectedFolder = new Folder("");
        folders.add(selectedFolder);
        testNote = new Note("sampleNote");
    }

    public String saveTestNote(String name, String content) {
        String statusOut = "Note saved";

        testNote.setName(name);
        testNote.setContent(content);

        return statusOut;
    }

    //TODO
    public String makeNote(String newNoteName, boolean saveCurrentNote) {
        String statusOut = "note Created";
        if(saveCurrentNote) {
            saveNote();
            statusOut = statusOut = ", "+selectedNote.getName()+" saved.";
        } else {
            statusOut = statusOut = ", "+selectedNote.getName()+" not saved.";
        }
        Note newNote = new Note(newNoteName);
        selectedFolder.addNote(newNote);
        selectedNote = newNote;
        return statusOut;
    }

    //TODO
    public String renameNote() {
        String statusOut = "note Renamed";

        return statusOut;
    }

    //TODO
    public String saveNote() {
        String statusOut = "note Saved";

        return statusOut;
    }

    //TODO
    public ArrayList<String> searchInFolderFor(String term) {
        ArrayList<String> out =  new ArrayList<String>();

        return out;
    }

    //TODO
    public Note getNoteFromFolder(String name) {
        Note out =  null;


        return out;
    }

    //TODO: This
    public String makeFolder() {
        String statusOut = "Folder Created";

        return statusOut;
    }
    //TODO: This
    public String renameFolder() {
        String statusOut = "Folder renamed";

        return statusOut;

    }

    private ArrayList<String> getFolderNames() {
        ArrayList<String> out =  new ArrayList<String>();
        for(Folder folder: folders) {
            out.add(folder.getFolderName());
        }
        return out;
    }
    public ArrayList<Folder> getFolders() { return folders; }
    public Folder getSelectedFolder() { return selectedFolder;}
    public Note getTestNote() { return testNote;}
    public Note getSelectedNote() { return selectedNote;}
}
