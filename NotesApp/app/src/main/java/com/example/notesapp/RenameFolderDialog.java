package com.example.notesapp;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class RenameFolderDialog extends AppCompatDialogFragment {
    private EditText editTextNewName;
    private folderDialogListener listener;



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);

        builder.setView(view)
                .setTitle("Rename Folder")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogeInterface, int i) {
                        listener.applyRenameFolderText("");
                    }

                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogeInterface, int i) {
                        String inText = editTextNewName.getText().toString();
                        listener.applyRenameFolderText(inText);
                    }

                });
        editTextNewName = view.findViewById(R.id.editName);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            listener = (folderDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "implement folderDialogListener");
        }
    }

    public interface folderDialogListener {
        void applyRenameFolderText(String newName);
    }
}
