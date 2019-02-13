package com.example.notesapp;

import java.util.ArrayList;

public class NoteAppHandler {
    private ArrayList<Folder> folders;
    private Folder selectedFolder;
    private Note selectedNote;

    public NoteAppHandler() {
        folders = new ArrayList<Folder>();
        selectedFolder = new Folder("newFolder");
        selectedNote = new Note("sampleNote");
        selectedNote.setContent("");
        selectedFolder.addNote(selectedNote);
        folders.add(selectedFolder);

    }

    public String makeNote(String name, String contentIn) {
        String statusOut = "Note created";
        boolean nameMatch = containsName(name, selectedFolder);
        if(!nameMatch) {
            Note newNote = new Note(name);
            selectedFolder.addNote(newNote);
            statusOut = "Note created and named " + name;
            openNote(name, contentIn);
            statusOut = statusOut + ", "+selectedNote.getName()+" saved.";
        } else {
            statusOut = "Name already used, note not saved";
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
                statusOut = "Another note is already named "+newName+", "+ selectedNote.getName() + " not renamed.";
            }
        } else {
            statusOut = "No new name given, name unchanged.";
        }
        return statusOut;
    }

    public String saveNote(String contentIn) {
        String statusOut = selectedNote.getName() + " saved.";
        selectedNote.setContent(contentIn);
        selectedFolder.addNote(selectedNote);
        return statusOut;
    }

    public String openNote(String name, String contentIn) {
        String statusOut;
        if(getNoteFromFolder(name)!=null) {
            saveNote(contentIn);
            statusOut = ", saved " + selectedNote.getName();
            selectedNote = getNoteFromFolder(name);
            statusOut = "Opened " + selectedNote.getName() + statusOut;
        } else {
            statusOut = "Note not found";
        }
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

    private boolean containsName(String name, Folder folder) {
        boolean out = false;
        for(Note test : folder.getNoteArray()) {
            if(test.getName().equals(name)) {
                out = true;
            }
        }
        return out;
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
    public Note getSelectedNote() { return selectedNote;}
}
