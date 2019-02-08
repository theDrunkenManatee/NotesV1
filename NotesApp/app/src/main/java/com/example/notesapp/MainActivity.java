package com.example.notesapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button newFolderButton;
    private Button renameFolderButton;
    private Button newNoteButton;
    private Button saveNoteButton;
    private Button loadNoteButton;
    private NoteAppHandler handler;

    private Spinner folderSpinner;
    private ArrayAdapter<String> folderAdapter;
    private Spinner noteSpinner;
    private ArrayAdapter<String> noteAdapter;

    private EditText keywordSearchText;
    private EditText noteText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Folder testFolder =  new Folder("testFolder");
        Note testNote1 = new Note("Note1");
        Note testNote2 = new Note("Note2");
        Note testNote3 = new Note("Note3");
        testFolder.addNote(testNote1);
        testFolder.addNote(testNote2);
        testFolder.addNote(testNote3);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new NoteAppHandler();

        newFolderButton = (Button) findViewById(R.id.newFolderButton);
        renameFolderButton = (Button) findViewById(R.id.renameFolderButton);
        newNoteButton = (Button) findViewById(R.id.newNoteButton);
        saveNoteButton = (Button) findViewById(R.id.saveNoteButton);
        loadNoteButton = (Button) findViewById(R.id.loadNoteButton);

        noteText = (EditText) findViewById(R.id.noteText);
        setNoteButtons();

        noteText.setText(testFolder.toString());

        handler.saveTestNote("SampleNote","");
    }

    private void setNoteButtons() {
        newNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noteText.setText("");
            }
        });
        saveNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.saveTestNote("SampleNote",noteText.getText().toString());
            }
        });
        loadNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noteText.setText(handler.getTestNote().getContent());
            }
        });
    }

    private void setSpinners(ArrayList<Folder> folderArrayList, ArrayList<Folder> noteArrayList) {
        folderSpinner = (Spinner) findViewById(R.id.folderList);
        //folderAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, folderArrayList);
        folderSpinner.setAdapter(folderAdapter);

        noteSpinner = (Spinner) findViewById(R.id.noteList);
        //noteAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, noteArrayList);
        noteSpinner.setAdapter(noteAdapter);
    }
}