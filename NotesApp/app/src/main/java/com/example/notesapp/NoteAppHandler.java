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

    public String makeNote(String name, String contentIn, boolean saveCurrentNote) {
        String statusOut = "Note created";

        Note newNote = new Note(name);

        boolean nameMatch = false;
        for(Note note : selectedFolder.getNoteArray()) {
            if(note.getName().equals(name)) {
                nameMatch = true;
            }
        }
        if(!nameMatch) {
            selectedFolder.addNote(newNote);
            statusOut = "Note created and named " + name;
        } else {
            statusOut = "Name already used, note not saved";
        }

        if(saveCurrentNote) {
            saveNote(contentIn);
            statusOut = statusOut = ", "+selectedNote.getName()+" saved.";
        } else {
            statusOut = statusOut = ", "+selectedNote.getName()+" not saved.";
        }
        if(!nameMatch) {
            selectedNote=newNote;
        }
        return statusOut;
    }

    public String renameNote(String newName) {
        String statusOut = "Note renamed";
        if(newName.length()>0) {
            String oldName = selectedNote.getName();
            boolean nameMatch = false;
            for(Note note : selectedFolder.getNoteArray()) {
                if(note.getName().equals(newName)) {
                    nameMatch = true;
                }
            }
            if(!nameMatch) {
                selectedNote.setName(newName);
                statusOut = statusOut + ", name changed from " + oldName + " to " + newName + ".";
            } else {
                statusOut = "Another note is already named "+newName+", current note not renamed.";
            }
        } else {
            statusOut = "No new name given, name unchanged.";
        }
        return statusOut;
    }

    public String saveNote(String contentIn) {
        String statusOut = "Note saved.";
        testNote.setContent(contentIn);
        return statusOut;
    }

    public ArrayList<Note> searchInFolderFor(String term) {
        ArrayList<Note> out =  new ArrayList<Note>();
        for(Note note : selectedFolder.getNoteArray()) {
            if(note.getName().contains(term)) {
                out.add(note);
            } else if(note.getContent().contains(term)) {
                out.add(note);
            }
        }
        return out;
    }

    public Note getNoteFromFolder(String name) {
        Note out =  null;
        if(selectedFolder.getNote(name) != null) {
            for(Note note : selectedFolder.getNoteArray()) {
                if(note.getName().equals(name)) {
                    out = note;
                }
            }
        }
        return out;
    }

    public String makeFolder(String name) {
        String statusOut = "Folder created";
        boolean nameMatch = false;
        for(Folder folder : folders) {
            if (folder.getFolderName().equals(name)) {
                nameMatch = true;
            }
        }
        if(!nameMatch) {
            Folder newFolder = new Folder(name);
            folders.add(newFolder);
            statusOut = statusOut + ", named " + name + ".";
        } else {
            statusOut = "Another folder is already named" + name + ", no new folder created.";
        }
        return statusOut;
    }

    public String renameFolder(String newName) {
        String statusOut = "Folder renamed";
        boolean nameMatch = false;
        for(Folder folder : folders) {
            if (folder.getFolderName().equals(newName)) {
                nameMatch = true;
            }
        }
        if(!nameMatch) {
            selectedFolder.setFolderName(newName);
            statusOut = statusOut + " to " +newName + ".";
        } else {
            statusOut = "Name already found, folder not renamed.";
        }
        return statusOut;
    }

    //Getter methods
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
