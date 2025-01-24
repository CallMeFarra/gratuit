package com.faradilla.gratuit;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddNoteActivity extends AppCompatActivity {

    private EditText titleInput, descriptionInput;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        titleInput = findViewById(R.id.inputTitle);
        descriptionInput = findViewById(R.id.inputDescription);
        Button saveButton = findViewById(R.id.buttonSave);

        databaseHelper = new DatabaseHelper(this);

        saveButton.setOnClickListener(v -> {
            String title = titleInput.getText().toString().trim();
            String description = descriptionInput.getText().toString().trim();

            if (title.isEmpty() || description.isEmpty()) {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            databaseHelper.addNote(title, description);
            Toast.makeText(this, "Note added successfully", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
