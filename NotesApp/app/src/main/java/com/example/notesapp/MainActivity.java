package com.example.notesapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RenameNoteDialog.noteDialogListener, RenameFolderDialog.folderDialogListener, NewNoteDialog.newNoteDialogListener, NewFolderDialog.newFolderDialogListener {

    private Button makeNoteButton;


    private Button saveNoteButton;

    private Button searchFolderButton;
    private EditText folderSearchTerm;

    private Button openNoteButton;
    private EditText openNoteName;

    private Button makeFolderButton;


    private TextView folderName;
    private TextView noteName;
    private EditText noteText;

    private NoteAppHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new NoteAppHandler();
        attachButtons();
        setNoteButtons();
        updateScreen();
    }

    private void attachButtons() {
        makeNoteButton = (Button) findViewById(R.id.makeNoteButton);

        saveNoteButton = (Button) findViewById(R.id.saveNoteButton);

        searchFolderButton = (Button) findViewById(R.id.searchFolderButton);
        folderSearchTerm = (EditText) findViewById(R.id.searchTerm);

        openNoteButton = (Button) findViewById(R.id.openNoteButton);
        openNoteName = (EditText) findViewById(R.id.openNoteName);

        makeFolderButton = (Button) findViewById(R.id.makeFolderButton);

        folderName = (TextView) findViewById(R.id.folderName);
        noteName = (TextView) findViewById(R.id.noteName);
        noteText = (EditText) findViewById(R.id.noteText);
    }

    private void setNoteButtons() {
        makeNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeNote();
            }
        });

        saveNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });

        searchFolderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String status = "";
                ArrayList<Note> out = handler.searchInFolderFor(folderSearchTerm.getText().toString());
                status = folderSearchTerm.getText().toString() + " found in the following:";
                for(Note note : out) {
                    status = status + "\n-" +note.getName();
                }
                updateScreen();
                Toast.makeText(getApplicationContext(), status, Toast.LENGTH_SHORT).show();
            }
        });

        openNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String status = handler.openNote(openNoteName.getText().toString(), noteText.getText().toString());
                updateScreen();
                Toast.makeText(getApplicationContext(), status, Toast.LENGTH_SHORT).show();
            }
        });

        noteName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                renameNote();
            }
        });

        folderName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                renameFolder();
            }
        });

        makeFolderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeFolder();
            }
        });
    }


    //Button Response Methods
    private void makeNote() {
        updateHandler();
        makeNewNoteDialog();
    }
    private void saveNote() {
        updateHandler();
        String status = handler.saveNote(noteText.getText().toString());
        updateScreen();
        Toast.makeText(getApplicationContext(), status, Toast.LENGTH_SHORT).show();
    }
    private void makeFolder() {
        updateHandler();
        makeNewFolderDialog();
    }
    private void renameNote() {
        updateHandler();
        makeRenameNoteDialog();
    }
    private void renameFolder() {
        updateHandler();
        makeRenameFolderDialog();
    }
    //TODO
    private void openNote() {
        updateHandler();
    }

    //Dialog Method Implementations
    @Override
    public void applyRenameNoteText(String newName) {
        if(newName!="") {
            String status = handler.renameNote(newName);
            handler.saveNote(noteText.getText().toString());
            Toast.makeText(getApplicationContext(), status, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "No new name given.", Toast.LENGTH_SHORT).show();
        }
        updateScreen();
    }
    @Override
    public void applyRenameFolderText(String newName) {
        if(newName!="") {
            String status = handler.renameFolder(newName);
            handler.saveNote(noteText.getText().toString());
            Toast.makeText(getApplicationContext(), status, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "No new name given.", Toast.LENGTH_SHORT).show();
        }
        updateScreen();
    }
    @Override
    public void applyNewNoteText(String newName) {
        String status = handler.makeNote(newName, noteText.getText().toString());
        updateScreen();
        Toast.makeText(getApplicationContext(), status, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void applyNewFolderText(String newName) {
        String status = handler.makeFolder(newName);
        updateScreen();
        Toast.makeText(getApplicationContext(), status, Toast.LENGTH_SHORT).show();
    }


    //Dialog Generation Methods
    public void makeRenameNoteDialog() {
        RenameNoteDialog exampleDialog = new RenameNoteDialog();
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }
    public void makeNewFolderDialog() {
        NewFolderDialog exampleDialog = new NewFolderDialog();
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }
    public void makeNewNoteDialog() {
        NewNoteDialog exampleDialog = new NewNoteDialog();
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }
    public void makeRenameFolderDialog() {
        RenameFolderDialog exampleDialog = new RenameFolderDialog();
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }

    //Update Methods
    private void updateHandler() {handler.setCurrentContent(noteText.getText().toString()); }
    private void updateScreen() {
        folderName.setText(handler.getSelectedFolder().getFolderName());
        noteName.setText(handler.getSelectedNote().getName());
        noteText.setText(handler.getSelectedNote().getContent());
    }
}